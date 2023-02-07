package com.example.mvvmfilmproject.data

import com.example.mvvmfilmproject.data.models.FilmData
import kotlinx.coroutines.delay
import javax.inject.Inject

class User @Inject constructor(
    private val service: Server,
) {
    suspend fun getUserFavoriteFilmList(): List<FilmData> {
        delay(500)
        return userFavouriteFilmList
    }

    fun addFilmToUserFavoriteFilms(id: Int) {
        userFavouriteFilmList.add(service.getFilmById(id - 1))
    }

    fun isFilmInFavouriteList(id: Int): Boolean {
        return userFavouriteFilmList.contains(service.getFilmById(id - 1))
    }

    fun deleteFilmFromFavouriteFilms(id: Int) {
        userFavouriteFilmList.remove(service.getFilmById(id - 1))
    }

    companion object {
        private val userFavouriteFilmList = mutableListOf<FilmData>()
    }
}