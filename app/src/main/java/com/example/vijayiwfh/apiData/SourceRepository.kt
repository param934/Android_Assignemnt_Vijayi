package com.example.vijayiwfh.apiData

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Toast
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class SourceRepository(private val application: Application, private val api: TMDbApi) {

    fun fetchMoviesAndTVShows(): Single<Pair<List<Movie>, List<TVShow>>> {
        if (!isNetworkAvailable(application)) {
            Toast.makeText(application, "No internet connection!", Toast.LENGTH_SHORT).show()
            return Single.error(Exception("No internet connection"))
        }
        return Single.zip(
            api.getMovies()
                .map { it.results }
                .subscribeOn(Schedulers.io()),
            api.getTVShows()
                .map { it.results }
                .subscribeOn(Schedulers.io())
        ) { movies, tvShows ->
            Pair(movies, tvShows)
        }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { error ->
                Log.e("SourceRepository", "Error fetching data", error)
                Toast.makeText(application, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        return connectivityManager?.let {
            val network = it.activeNetwork ?: return false
            val activeNetwork = it.getNetworkCapabilities(network) ?: return false
            activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } ?: false
    }
}
