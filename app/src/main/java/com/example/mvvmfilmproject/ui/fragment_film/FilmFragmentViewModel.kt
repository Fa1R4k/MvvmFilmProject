package com.example.mvvmfilmproject.ui.fragment_film

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmfilmproject.R
import com.example.mvvmfilmproject.domain.FilmRecyclerData
import com.example.mvvmfilmproject.domain.FilmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class FilmFragmentViewModel @Inject constructor(
    private val repository: FilmRepository,
) : ViewModel() {

    private val _liveData = MutableLiveData<List<FilmRecyclerData>>()
    val liveData: LiveData<List<FilmRecyclerData>> get() = _liveData

    private val _errorLiveData = MutableLiveData<Int>()
    val errorLiveData: LiveData<Int> get() = _errorLiveData

    private val _progressLiveData = MutableLiveData<Boolean>()
    val progressLiveData: LiveData<Boolean> get() = _progressLiveData

    private val exception = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is SocketTimeoutException -> _errorLiveData.value = R.string.socket_error
        }
    }

    fun getFilms() {
        _progressLiveData.value = true
        viewModelScope.launch(exception) {
            val films = repository.getFilms()
            _liveData.value = films
            _progressLiveData.value = false
        }
    }
}