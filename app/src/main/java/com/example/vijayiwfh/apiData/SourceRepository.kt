import android.app.Application
import android.util.Log
import com.example.vijayiwfh.apiData.Movie
import com.example.vijayiwfh.apiData.TVShow
import com.example.vijayiwfh.apiData.isNetworkAvailable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class SourceRepository(private val application: Application) {

    private val api = WatchmodeService.api

    fun fetchMoviesAndTVShows(): Single<Pair<List<Movie>, List<TVShow>>> {
        val movieUrl = "https://api.watchmode.com/v1/list-titles/?apiKey=${ApiConfig.API_KEY}&type=movie&page=1&limit=10"
        val tvShowUrl = "https://api.watchmode.com/v1/list-titles/?apiKey=${ApiConfig.API_KEY}&type=tvshows&page=1&limit=10"

        Log.d("API_REQUEST", "Fetching movies from: $movieUrl")
        Log.d("API_REQUEST", "Fetching TV shows from: $tvShowUrl")

        return Single.zip(
            api.getMovies()
                .doOnSubscribe { Log.d("API_CALL", "Calling Movies API...") }
                .doOnSuccess { response -> Log.d("API_RAW_RESPONSE", "Movies Response: $response") }
                .map { it.movies ?: emptyList() }
                .doOnError { error -> Log.e("API_ERROR", "Error fetching movies: ${error.message}", error) }
                .subscribeOn(Schedulers.io()),

            api.getTVShows()
                .doOnSubscribe { Log.d("API_CALL", "Calling TV Shows API...") }
                .doOnSuccess { response -> Log.d("API_RAW_RESPONSE", "TV Shows Response: $response") }
                .map { it.tvShows ?: emptyList() }
                .doOnError { error -> Log.e("API_ERROR", "Error fetching TV shows: ${error.message}", error) }
                .subscribeOn(Schedulers.io())
        ) { movies, tvShows ->
            Log.d("API_FINAL", "Final Movies Count: ${movies.size}")
            Log.d("API_FINAL", "Final TV Shows Count: ${tvShows.size}")
            Pair(movies, tvShows)
        }
            .doOnError { error -> Log.e("API_ERROR", "Error in final API processing: ${error.message}", error) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}
