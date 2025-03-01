package com.example.vijayiwfh

import android.content.Context
import android.net.Uri

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.valentinilk.shimmer.shimmer
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.vijayiwfh.apiData.Movie
import com.example.vijayiwfh.apiData.SourceViewModel
import com.example.vijayiwfh.apiData.TVShow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: SourceViewModel, navController: NavHostController) {
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
    val tabs = listOf("Movies", "TV Shows")
    val pagerState = rememberPagerState(
        initialPage = selectedTab,
        initialPageOffsetFraction = 0f,
        pageCount = { tabs.size }
    )
    val coroutineScope = rememberCoroutineScope()

    val movies by viewModel.movies.observeAsState(emptyList())
    val tvShows by viewModel.tvShows.observeAsState(emptyList())

    LaunchedEffect(pagerState.currentPage) {
        selectedTab = pagerState.currentPage
    }

    Scaffold(
        topBar = {
            Column {
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
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) { page ->
                Column(modifier = Modifier.fillMaxSize()) {
                    when (page) {
                        0 -> MovieList(movies, navController)
                        1 -> TVShowList(tvShows, navController)
                    }
                }
            }
        }
    }
}

@Composable
fun MovieList(movies: List<Movie>, navController: NavHostController) {
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(movies) {
        kotlinx.coroutines.delay(5000)
        if (movies.isNotEmpty()) {
            isLoading = false
        }
    }

    LazyColumn {
        if (isLoading) {
            items(5) { ShimmerCard() }
        } else {
            items(movies) { movie ->
                InfoCard(
                    title = movie.title,
                    overview = movie.overview,
                    onClick = {
                        val encodedTitle = Uri.encode(movie.title)
                        val encodedOverview = Uri.encode(movie.overview)
                        val encodedPosterPath = Uri.encode(movie.poster_path ?: "")
                        val encodedBackdropPath = Uri.encode(movie.backdrop_path ?: "")
                        val encodedReleaseDate = Uri.encode(movie.release_date)
                        val encodedVoteAverage = Uri.encode(movie.vote_average.toString())
                        val encodedVoteCount = Uri.encode(movie.vote_count.toString())
                        val encodedLanguage = Uri.encode(movie.original_language)
                        val encodedPopularity = Uri.encode(movie.popularity.toString())
                        val encodedGenres = Uri.encode(movie.genre_ids.joinToString(","))

                        navController.navigate(
                            "details/$encodedTitle/$encodedOverview/$encodedPosterPath/$encodedBackdropPath/$encodedReleaseDate/" +
                                    "$encodedVoteAverage/$encodedVoteCount/$encodedLanguage/$encodedPopularity/$encodedGenres"
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun TVShowList(tvShows: List<TVShow>, navController: NavHostController) {
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(tvShows) {
        kotlinx.coroutines.delay(5000)
        if (tvShows.isNotEmpty()) {
            isLoading = false
        }
    }

    LazyColumn {
        if (isLoading) {
            items(5) { ShimmerCard() }
        } else {
            items(tvShows) { tvShow ->
                InfoCard(
                    title = tvShow.name,
                    overview = tvShow.overview,
                    onClick = {
                        val encodedTitle = Uri.encode(tvShow.name)
                        val encodedOverview = Uri.encode(tvShow.overview)
                        val encodedPosterPath = Uri.encode(tvShow.poster_path ?: "")
                        val encodedBackdropPath = Uri.encode(tvShow.backdrop_path ?: "")
                        val encodedReleaseDate = Uri.encode(tvShow.first_air_date)
                        val encodedVoteAverage = Uri.encode(tvShow.vote_average.toString())
                        val encodedVoteCount = Uri.encode(tvShow.vote_count.toString())
                        val encodedLanguage = Uri.encode(tvShow.original_language)
                        val encodedPopularity = Uri.encode(tvShow.popularity.toString())
                        val encodedGenres = Uri.encode(tvShow.genre_ids.joinToString(","))

                        navController.navigate(
                            "details/$encodedTitle/$encodedOverview/$encodedPosterPath/$encodedBackdropPath/$encodedReleaseDate/" +
                                    "$encodedVoteAverage/$encodedVoteCount/$encodedLanguage/$encodedPopularity/$encodedGenres"
                        )
                    }
                )
            }
        }
    }
}


@Composable
fun InfoCard(title: String, overview: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
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
                text = overview,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}


@Composable
fun ShimmerCard() {
    val shimmerModifier = Modifier
        .fillMaxWidth()
        .height(120.dp)
        .clip(MaterialTheme.shapes.medium)
        .shimmer()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(modifier = shimmerModifier.background(Color.Gray.copy(alpha = 0.3f)))
        }
    }
}

