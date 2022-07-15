package com.example.themoviedbkotlin.data.mapper

import com.example.themoviedbkotlin.data.model.MovieListResponse
import com.example.themoviedbkotlin.domain.entity.MovieListResponseEntity
import javax.inject.Inject

class MovieListResponseMapper @Inject constructor(
    private val movieMapper: MovieMapper
) : ModelMapper<MovieListResponse, MovieListResponseEntity> {

    override fun mapToEntity(model: MovieListResponse): MovieListResponseEntity =
        MovieListResponseEntity(
            page = model.page,
            totalResults = model.totalResults,
            totalPage = model.totalPage,
            results = model.results?.map { movie -> movieMapper.mapToEntity(movie) }
        )
}