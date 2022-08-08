package com.example.themoviedbkotlin.data.repository_impl

import com.example.themoviedbkotlin.data.mapper.MovieListResponseMapper
import com.example.themoviedbkotlin.data.remote.ApiService
import com.example.themoviedbkotlin.di.IODispatcher
import com.example.themoviedbkotlin.domain.entity.MovieListResponseEntity
import com.example.themoviedbkotlin.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    @IODispatcher private val dispatcher: CoroutineDispatcher,
) : MovieRepository {

    override suspend fun getTrendingMovie(params: HashMap<String, String>): MovieListResponseEntity =
        withContext(dispatcher) {
            MovieListResponseMapper.mapToEntity(model = apiService.getTrendingMovie(params = params))
        }

    override suspend fun getPopularMovie(params: HashMap<String, String>): MovieListResponseEntity =
        withContext(dispatcher) {
            MovieListResponseMapper.mapToEntity(model = apiService.getPopularMovie(params = params))
        }

    override suspend fun getUpcomingMovie(params: HashMap<String, String>): MovieListResponseEntity =
        withContext(dispatcher) {
            MovieListResponseMapper.mapToEntity(model = apiService.getUpcomingMovie(params = params))
        }
}