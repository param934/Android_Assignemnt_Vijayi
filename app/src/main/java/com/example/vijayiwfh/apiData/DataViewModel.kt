package com.example.vijayiwfh.apiData

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SourceViewModel(application: Application) : AndroidViewModel(application), KoinComponent {
    private val repository: SourceRepository by inject() // Inject Repository
    private val compositeDisposable = CompositeDisposable()

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    private val _tvShows = MutableLiveData<List<TVShow>>()
    val tvShows: LiveData<List<TVShow>> get() = _tvShows

    init {
        fetchData()
    }

    fun fetchData() {
        repository.fetchMoviesAndTVShows()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                _movies.value = result.first
                _tvShows.value = result.second
            }, { error ->
                Toast.makeText(getApplication(), "Error fetching data: ${error.message}", Toast.LENGTH_SHORT).show()
            }).also { compositeDisposable.add(it) }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
