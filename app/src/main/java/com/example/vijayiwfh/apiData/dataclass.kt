package com.example.vijayiwfh.apiData

data class MoviesResponse(
    val page: Int,
    val results: List<Movie> = emptyList(),
    val total_pages: Int,
    val total_results: Int
)

data class TVShowsResponse(
    val page: Int,
    val results: List<TVShow> = emptyList(),
    val total_pages: Int,
    val total_results: Int
)

data class Movie(
    val id: Int,
    val title: String,
    val original_title: String,
    val overview: String,
    val release_date: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val vote_average: Double,
    val vote_count: Int,
    val popularity: Double,
    val genre_ids: List<Int>,
    val original_language: String
)

data class TVShow(
    val id: Int,
    val name: String,
    val original_name: String,
    val overview: String,
    val first_air_date: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val vote_average: Double,
    val vote_count: Int,
    val popularity: Double,
    val genre_ids: List<Int>,
    val original_language: String
)
