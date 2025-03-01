package com.example.vijayiwfh

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vijayiwfh.apiData.Movie
import com.example.vijayiwfh.apiData.SourceViewModel
import com.example.vijayiwfh.apiData.TVShow
import kotlinx.coroutines.launch

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(viewModel: SourceViewModel) {
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
    val tabs = listOf("Movies", "TV Shows")
    val pagerState = rememberPagerState(
        initialPage = selectedTab,
        initialPageOffsetFraction = 0f,
        pageCount = { tabs.size }
    )
    val coroutineScope = rememberCoroutineScope()

    // States to hold movies and TV shows data.
    val movies by viewModel.movies.observeAsState(emptyList())
    val tvShows by viewModel.tvShows.observeAsState(emptyList())
    var isLoading by remember { mutableStateOf(false) }

    // Keep the selected tab in sync with the pager's current page.
    if (selectedTab != pagerState.currentPage) {
        selectedTab = pagerState.currentPage
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home") },
                actions = {
                    TabRow(selectedTabIndex = selectedTab) {
                        tabs.forEachIndexed { index, title ->
                            Tab(
                                selected = index == selectedTab,
                                onClick = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(index)
                                    }
                                },
                                text = { Text(title, color = MaterialTheme.colorScheme.primary) }
                            )
                        }
                    }
                }
            )
        },
        content = { padding ->
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) { page ->
                Column(modifier = Modifier.fillMaxSize()) {

                    if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                    } else {
                        when (page) {

                            0 -> {
                                if (movies.isEmpty()) {
                                    Text("No movies available", color = MaterialTheme.colorScheme.primary)
                                } else {
                                    movies.forEach { movie ->
                                        InfoCard(
                                            title = movie.title,
                                            description = movie.title
                                        )
                                    }
                                }
                            }
                            1 -> {
                                if (tvShows.isEmpty()) {
                                    Text("No TV shows available", color = MaterialTheme.colorScheme.primary)
                                } else {
                                    tvShows.forEach { tvShow ->
                                        InfoCard(
                                            title = tvShow.name,
                                            description = tvShow.name
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}


@Composable
fun InfoCard(title: String, description: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
