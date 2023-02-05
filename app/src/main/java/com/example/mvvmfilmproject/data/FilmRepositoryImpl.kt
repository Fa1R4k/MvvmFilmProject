package com.example.mvvmfilmproject.data

import com.example.mvvmfilmproject.data.mappers.FilmDescriptionMapper
import com.example.mvvmfilmproject.data.mappers.FilmRecyclerMapper
import com.example.mvvmfilmproject.domain.FilmDescriptionData
import com.example.mvvmfilmproject.domain.FilmRecyclerData
import com.example.mvvmfilmproject.domain.FilmRepository
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    private val service: Server,
    private val recyclerMapper: FilmRecyclerMapper,
    private val descriptionMapper: FilmDescriptionMapper,
) : FilmRepository {


    override fun getFilms(): List<FilmRecyclerData> {
        return service.getFilms().map { recyclerMapper(it) }.toList()
    }

    override fun getFilmDescriptionById(id : Int): FilmDescriptionData {
        return descriptionMapper(service.getFilmDescription(id))
    }
}