package com.example.vijayiwfh.di

import com.example.vijayiwfh.apiData.SourceRepository
import com.example.vijayiwfh.apiData.SourceViewModel
import com.example.vijayiwfh.apiData.TMDbService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { TMDbService.api } // Provide API instance
    single { SourceRepository(get(),get()) } // Inject API into Repository
    viewModel { SourceViewModel(get()) } // Inject Repository into ViewModel
}
