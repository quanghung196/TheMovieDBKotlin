package com.example.themoviedbkotlin.ui.home

import com.example.themoviedbkotlin.domain.entity.MovieEntity
import com.example.themoviedbkotlin.domain.use_case.GetPopularMovieUseCase
import com.example.themoviedbkotlin.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMovieUseCase: GetPopularMovieUseCase
) : BaseViewModel() {

    private val _movies = MutableStateFlow<List<MovieEntity>>(emptyList())
    val movies = _movies.asStateFlow()

    fun getMovieList() {
        if (_movies.value.isNotEmpty()) return
        val params = HashMap<String, String>()
        params["page"] = "1"
        viewModelScopeExceptionHandler.launch {
            _movies.emit(getPopularMovieUseCase.invoke(params = params).results ?: emptyList())
        }
    }
}