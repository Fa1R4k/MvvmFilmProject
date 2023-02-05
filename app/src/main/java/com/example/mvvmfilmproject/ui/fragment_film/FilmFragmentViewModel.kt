package com.example.mvvmfilmproject.ui.fragment_film

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmfilmproject.domain.FilmRecyclerData
import com.example.mvvmfilmproject.domain.FilmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilmFragmentViewModel @Inject constructor(
    private val repository: FilmRepository,
) : ViewModel() {

    private val _liveData = MutableLiveData<List<FilmRecyclerData>>()
    val liveData: LiveData<List<FilmRecyclerData>> get() = _liveData

    fun getFilms() {
        val films = repository.getFilms()
        _liveData.value = films
    }
}