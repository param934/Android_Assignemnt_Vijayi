package com.example.vijayiwfh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.vijayiwfh.apiData.SourceViewModel
import com.example.vijayiwfh.ui.theme.VijayiWFHTheme

class MainActivity : ComponentActivity() {

    // Initialize ViewModel using Jetpack Compose delegate
    private val viewModel: SourceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VijayiWFHTheme {
                HomeScreen(viewModel = viewModel) // ✅ Pass ViewModel to Composable
            }
        }

        // Fetch data when activity is created
        viewModel.fetchData()
    }
}
