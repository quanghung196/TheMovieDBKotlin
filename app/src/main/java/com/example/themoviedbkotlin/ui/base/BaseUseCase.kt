package com.example.themoviedbkotlin.ui.base

abstract class BaseUseCase<RP> {
    abstract suspend operator fun invoke(params: HashMap<String, String> = HashMap()): RP
}