package com.example.mvvmfilmproject.domain

data class FilmRecyclerData(
    val id: Int,
    val imageLink: String,
    val name: String,
    val rating: String,
    val isOskar: Boolean,
)