package com.example.vijayiwfh

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.example.vijayiwfh.apiData.SourceViewModel
import com.example.vijayiwfh.di.appModule
import com.example.vijayiwfh.ui.theme.DetailsScreen
import com.example.vijayiwfh.ui.theme.VijayiWFHTheme
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.compose.koinViewModel
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : ComponentActivity() {
    private val viewModel: SourceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VijayiWFHTheme {
                val navController = rememberNavController()
                val viewModel: SourceViewModel = koinViewModel() // Inject ViewModel using Koin
                AppNavHost(navController, viewModel)
            }
        }
        viewModel.fetchData()
    }
}

@Composable
fun AppNavHost(navController: NavHostController, viewModel: SourceViewModel) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(viewModel = viewModel, navController = navController)
        }
        composable("details/{title}/{overview}/{posterPath}/{backdropPath}/{releaseDate}/{voteAverage}/{voteCount}/{originalLanguage}/{popularity}/{genreIds}") { backStackEntry ->
            val overview = backStackEntry.arguments?.getString("overview") ?: ""
            val posterPath = backStackEntry.arguments?.getString("posterPath") ?: ""
            val backdropPath = backStackEntry.arguments?.getString("backdropPath") ?: ""
            val releaseDate = backStackEntry.arguments?.getString("releaseDate") ?: ""
            val voteAverage = backStackEntry.arguments?.getString("voteAverage") ?: ""
            val voteCount = backStackEntry.arguments?.getString("voteCount") ?: ""
            val originalLanguage = backStackEntry.arguments?.getString("originalLanguage") ?: ""
            val popularity = backStackEntry.arguments?.getString("popularity") ?: ""
            val genreIds = backStackEntry.arguments?.getString("genreIds") ?: ""

            DetailsScreen(
                overview, posterPath, backdropPath, releaseDate, voteAverage, voteCount, originalLanguage, popularity, genreIds, navController
            )
        }
    }
}
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}