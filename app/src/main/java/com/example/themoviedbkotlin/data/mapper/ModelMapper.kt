package com.example.themoviedbkotlin.data.mapper

interface ModelMapper<M, E> {

    fun mapToEntity(model: M): E
}