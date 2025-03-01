package com.example.vijayiwfh.apiData

import SourceRepository
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import android.app.Application
import androidx.lifecycle.AndroidViewModel

class SourceViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = SourceRepository(application)
    private val compositeDisposable = CompositeDisposable()

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    private val _tvShows = MutableLiveData<List<TVShow>>()
    val tvShows: LiveData<List<TVShow>> get() = _tvShows
    init {
        fetchData()
    }
    fun fetchData() {

        val disposable = repository.fetchMoviesAndTVShows()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Log.d("API_RESPONSE", "Movies: ${result.first}")
                Log.d("API_RESPONSE", "TV Shows: ${result.second}")

                _movies.value = result.first
                _tvShows.value = result.second
            }, { error ->
                Log.e("API_ERROR", "Error: ${error.message}", error)
            })

        compositeDisposable.add(disposable)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}


fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    if (connectivityManager != null) {
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
    return false
}
