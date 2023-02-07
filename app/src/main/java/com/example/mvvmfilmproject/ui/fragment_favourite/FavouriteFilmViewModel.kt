package com.example.mvvmfilmproject.ui.fragment_favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmfilmproject.domain.FilmRecyclerData
import com.example.mvvmfilmproject.domain.FilmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteFilmViewModel @Inject constructor(
    private val repository: FilmRepository,
) : ViewModel() {

    private val _liveData = MutableLiveData<List<FilmRecyclerData>>()
    val liveData: LiveData<List<FilmRecyclerData>> get() = _liveData

    private val _progressLiveData = MutableLiveData<Boolean>()
    val progressLiveData: LiveData<Boolean> get() = _progressLiveData

    fun getFavoriteFilms() {
        _progressLiveData.value = true
        viewModelScope.launch() {
            _liveData.value = repository.getFavouriteFilms()
            _progressLiveData.value = false
        }
    }
}