package com.example.mvvmfilmproject.ui.fragment_description

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmfilmproject.domain.FilmDescriptionData
import com.example.mvvmfilmproject.domain.FilmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmDescriptionViewModel @Inject constructor(
    private val repository: FilmRepository,
) : ViewModel() {

    private val _liveData = MutableLiveData<FilmDescriptionData>()
    val liveData: LiveData<FilmDescriptionData> get() = _liveData

    private val _favouriteLiveData = MutableLiveData<Boolean>()
    val favouriteLiveData: LiveData<Boolean> get() = _favouriteLiveData

    fun getFilmDescription(id: Int) {
        val filmDescription = repository.getFilmDescriptionById(id)
        _liveData.value = filmDescription
        _favouriteLiveData.value = isFavoriteFilmInList(id)
    }

    private fun isFavoriteFilmInList(id: Int): Boolean {
        var contains = false
        viewModelScope.launch {
            contains = repository.isFilmInFavoriteList(id)
        }
        return contains
    }

    fun addFavouriteFilm(id: Int) {
        viewModelScope.launch {
            repository.addFavouriteFilm(id)
        }
    }

    fun deleteFavouriteFilm(id: Int) {
        viewModelScope.launch {
            repository.deleteFavouriteFilm(id)
        }
    }
}