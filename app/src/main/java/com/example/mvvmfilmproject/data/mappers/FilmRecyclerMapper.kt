package com.example.mvvmfilmproject.data.mappers

import com.example.mvvmfilmproject.data.models.FilmData
import com.example.mvvmfilmproject.domain.FilmRecyclerData
import javax.inject.Inject

class FilmRecyclerMapper @Inject constructor() {

    operator fun invoke(response: FilmData): FilmRecyclerData = with(response) {
        FilmRecyclerData(
            id = id,
            imageLink = imageLink,
            name = name,
            rating = rating,
            isOskar = isOskar)
    }
}