package com.example.themoviedbkotlin.data.mapper

import com.example.themoviedbkotlin.data.model.Movie
import com.example.themoviedbkotlin.domain.entity.MovieEntity

object MovieMapper : ModelMapper<Movie, MovieEntity> {
    override fun mapToEntity(model: Movie): MovieEntity = MovieEntity(
        id = model.id,
        adult = model.adult,
        backdropPath = model.backdropPath,
        budget = model.budget,
        homepage = model.homepage,
        imdbID = model.imdbID,
        originalLanguage = model.originalLanguage,
        originalTitle = model.originalTitle,
        overview = model.overview,
        popularity = model.popularity,
        posterPath = model.posterPath,
        releaseDate = model.releaseDate,
        revenue = model.revenue,
        runtime = model.runtime,
        status = model.status,
        tagline = model.tagline,
        title = model.title,
        video = model.video,
        voteAverage = model.voteAverage,
        voteCount = model.voteCount
    )
}