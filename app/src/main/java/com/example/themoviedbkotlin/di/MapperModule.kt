package com.example.themoviedbkotlin.di

import com.example.themoviedbkotlin.data.mapper.MovieListResponseMapper
import com.example.themoviedbkotlin.data.mapper.MovieMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MapperModule {
    @Singleton
    @Provides
    fun provideMovieMapper(): MovieMapper = MovieMapper()

    @Singleton
    @Provides
    fun provideMovieListResponseMapper(): MovieListResponseMapper =
        MovieListResponseMapper(movieMapper = MovieMapper())
}
