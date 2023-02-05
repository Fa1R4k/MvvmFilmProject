package com.example.mvvmfilmproject.data.mappers

import com.example.mvvmfilmproject.data.model.models.FilmData
import com.example.mvvmfilmproject.domain.FilmDescriptionData
import javax.inject.Inject

class FilmDescriptionMapper @Inject constructor() {

    operator fun invoke(response: FilmData): FilmDescriptionData = with(response) {
        FilmDescriptionData(
            id = id,
            imageLink = imageLink,
            name = name,
            rating = rating,
            description = description)
    }
}