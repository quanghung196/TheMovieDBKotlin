package com.example.themoviedbkotlin.data.model

data class MovieListResponse(
    val page: Int? = null,
    val totalResults: Int? = null,
    val totalPage: Int? = null,
    val results: List<Movie>? = null
)