package com.example.mvvmfilmproject.data.model.models

data class FilmData(
    val id: Int,
    val imageLink: String,
    val name: String,
    val rating: String,
    val isOskar: Boolean,
    val description: String
    )