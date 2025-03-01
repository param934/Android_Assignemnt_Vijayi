package com.example.vijayiwfh.apiData

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import io.reactivex.rxjava3.core.Single
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.http.Query

interface TMDbApi {
    @GET("movie/popular")
    fun getMovies(
        @Query("api_key") apiKey: String = ApiConfig.API_KEY,
        @Query("page") page: Int = 1
    ): Single<MoviesResponse>

    @GET("tv/popular")
    fun getTVShows(
        @Query("api_key") apiKey: String = ApiConfig.API_KEY,
        @Query("page") page: Int = 1
    ): Single<TVShowsResponse>
}

// Retrofit Service
object TMDbService {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    val api: TMDbApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(TMDbApi::class.java)
    }
}
// API Key Configuration
object ApiConfig {
    const val API_KEY = "ac5d416087c35acb061f5c918c64a4a3"
}

