package com.example.themoviedbkotlin.data.model

import com.example.themoviedbkotlin.domain.entity.MovieEntity


data class Movie(
    val id: String,
    val adult: Boolean? = false,
    val backdropPath: String? = null,
    val budget: Int? = null,
    val homepage: String? = null,
    val imdbID: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean? = false,
    val voteAverage: Double? = null,
    val voteCount: Int? = null,
)
