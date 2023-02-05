package com.example.mvvmfilmproject.ui.fragment_description

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmfilmproject.domain.FilmDescriptionData
import com.example.mvvmfilmproject.domain.FilmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class FilmDescriptionViewModel @Inject constructor(
    private val repository: FilmRepository,
) : ViewModel() {

    private val _liveData = MutableLiveData<FilmDescriptionData>()
    val liveData: LiveData<FilmDescriptionData> get() = _liveData

    fun getFilmDescription(id:Int) {
        val filmDescription = repository.getFilmDescriptionById(id)
        _liveData.value = filmDescription
    }
}