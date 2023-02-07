package com.example.mvvmfilmproject.data

import com.example.mvvmfilmproject.data.mappers.FilmDescriptionMapper
import com.example.mvvmfilmproject.data.mappers.FilmRecyclerMapper
import com.example.mvvmfilmproject.domain.FilmDescriptionData
import com.example.mvvmfilmproject.domain.FilmRecyclerData
import com.example.mvvmfilmproject.domain.FilmRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    private val service: Server,
    private val user: User,
    private val recyclerMapper: FilmRecyclerMapper,
    private val descriptionMapper: FilmDescriptionMapper,
) : FilmRepository {


    override suspend fun getFilms(): List<FilmRecyclerData> = withContext(Dispatchers.IO) {
        service.getFilms().map { recyclerMapper(it) }.toList()
    }

    override suspend fun getFavouriteFilms(): List<FilmRecyclerData> = withContext(Dispatchers.IO) {
        user.getUserFavoriteFilmList().map { recyclerMapper(it) }.toList()
    }

    override fun getFilmDescriptionById(id: Int): FilmDescriptionData {
        return descriptionMapper(service.getFilmDescription(id))
    }

    override suspend fun isFilmInFavoriteList(id: Int): Boolean {
        return user.isFilmInFavouriteList(id)
    }

    override suspend fun addFavouriteFilm(id: Int) {
        user.addFilmToUserFavoriteFilms(id)
    }

    override suspend fun deleteFavouriteFilm(id: Int) {
        user.deleteFilmFromFavouriteFilms(id)
    }
}