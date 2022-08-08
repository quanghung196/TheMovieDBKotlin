package com.example.themoviedbkotlin.data.remote

import com.example.themoviedbkotlin.data.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {
    @GET(ApiConstant.POPULAR_MOVIE)
    suspend fun getPopularMovie(@QueryMap params: HashMap<String, String> = HashMap()): MovieListResponse

    @GET(ApiConstant.TRENDING_MOVIE)
    suspend fun getTrendingMovie(@QueryMap params: HashMap<String, String> = HashMap()): MovieListResponse

    @GET(ApiConstant.UPCOMING_MOVIE)
    suspend fun getUpcomingMovie(@QueryMap params: HashMap<String, String> = HashMap()): MovieListResponse
}