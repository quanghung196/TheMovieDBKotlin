package com.example.themoviedbkotlin.domain.entity

import com.squareup.moshi.Json

class MovieListResponseEntity (
    @Json(name = "page") val page: Int? = null,
    @Json(name = "total_results") val totalResults: Int? = null,
    @Json(name = "total_page") val totalPage: Int? = null,
    @Json(name = "results") val results: List<MovieEntity>? = null,
)