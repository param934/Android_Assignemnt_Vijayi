package com.example.vijayiwfh.apiData

data class MoviesResponse(
    val movies: List<Movie>? = emptyList()  // Default empty list
)

data class TVShowsResponse(
    val tvShows: List<TVShow>? = emptyList()  // Default empty list
)


data class Movie(
    val id: Int,
    val title: String
)


data class TVShow(
    val id: Int,
    val name: String
)
