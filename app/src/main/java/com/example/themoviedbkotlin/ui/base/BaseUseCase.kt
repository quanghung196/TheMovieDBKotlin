package com.example.themoviedbkotlin.ui.base

abstract class BaseUseCase<RP> {
    protected abstract suspend fun invoke(params: HashMap<String, String>?) : RP
}