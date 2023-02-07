package com.example.mvvmfilmproject.domain

interface FilmRepository {

    suspend fun getFilms(): List<FilmRecyclerData>

    suspend fun getFavouriteFilms(): List<FilmRecyclerData>

    fun getFilmDescriptionById(id: Int): FilmDescriptionData

    suspend fun addFavouriteFilm(id: Int)

    suspend fun deleteFavouriteFilm(id: Int)

    suspend fun isFilmInFavoriteList(id: Int): Boolean
}