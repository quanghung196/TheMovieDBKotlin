package com.example.themoviedbkotlin.domain.repository

import com.example.themoviedbkotlin.domain.entity.MovieListResponseEntity

interface MovieRepository {
    suspend fun getTrendingMovie(params: HashMap<String, String>?): MovieListResponseEntity
    suspend fun getPopularMovie(params: HashMap<String, String>?): MovieListResponseEntity
    suspend fun getUpcomingMovie(params: HashMap<String, String>?): MovieListResponseEntity
}