package com.example.themoviedbkotlin.domain.use_case

import com.example.themoviedbkotlin.domain.entity.MovieListResponseEntity
import com.example.themoviedbkotlin.domain.repository.MovieRepository
import com.example.themoviedbkotlin.ui.base.BaseUseCase
import javax.inject.Inject

class GetPopularMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) : BaseUseCase<MovieListResponseEntity>() {
    public override suspend fun invoke(params: HashMap<String, String>?): MovieListResponseEntity =
        repository.getPopularMovie(params = params)

}