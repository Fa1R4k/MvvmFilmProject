package com.example.mvvmfilmproject.domain

interface FilmRepository {

    fun getFilms(): List<FilmRecyclerData>

    fun getFilmDescriptionById(id : Int): FilmDescriptionData
}