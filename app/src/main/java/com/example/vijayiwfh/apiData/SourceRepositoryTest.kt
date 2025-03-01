package com.example.vijayiwfh.apiData
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import io.mockk.every
//import io.mockk.mockk
//import io.reactivex.rxjava3.core.Single
//import org.junit.Assert.assertEquals
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.koin.core.context.startKoin
//import org.koin.core.context.stopKoin
//import org.koin.dsl.module
//import org.koin.test.KoinTestRule
//import org.koin.test.inject
//
//class SourceViewModelTest {
//
//    @get:Rule
//    val instantExecutorRule = InstantTaskExecutorRule()
//
//    @get:Rule
//    val koinTestRule = KoinTestRule.create {
//        modules(
//            module {
//                single { mockk<SourceRepository>() }
//                viewModel { SourceViewModel(get()) }
//            }
//        )
//    }
//
//    private val repository: SourceRepository by inject()
//    private lateinit var viewModel: SourceViewModel
//
//    @Before
//    fun setup() {
//        every { repository.fetchMoviesAndTVShows() } returns Single.just(
//            Pair(
//                listOf(Movie(1, "MovieTest", "Original", "Overview", "2024-10-01", null, null, 8.0, 100, 2000.0, listOf(1, 2), "en")),
//                listOf(TVShow(1, "TVShowTest", "Original", "Overview", "2024-09-01", null, null, 7.5, 50, 1800.0, listOf(3, 4), "en"))
//            )
//        )
//
//        viewModel = SourceViewModel(mockk(relaxed = true))
//    }
//
//    @Test
//    fun `fetchData should update LiveData with movies and tv shows`() {
//        viewModel.fetchData()
//
//        assertEquals(1, viewModel.movies.value?.size)
//        assertEquals(1, viewModel.tvShows.value?.size)
//        assertEquals("MovieTest", viewModel.movies.value?.first()?.title)
//        assertEquals("TVShowTest", viewModel.tvShows.value?.first()?.name)
//    }
//}
