import com.example.vijayiwfh.apiData.MoviesResponse
import com.example.vijayiwfh.apiData.TVShowsResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import io.reactivex.rxjava3.core.Single
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.http.Query

interface WatchmodeApi {

    @GET("list-titles/")
    fun getMovies(
        @Query("apiKey") apiKey: String = ApiConfig.API_KEY,
        @Query("type") type: String = "movie",
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ): Single<MoviesResponse>

    @GET("list-titles/")
    fun getTVShows(
        @Query("apiKey") apiKey: String = ApiConfig.API_KEY,
        @Query("type") type: String = "tv_series",
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ): Single<TVShowsResponse>
}

// Store API key in a separate object
object ApiConfig {
    const val API_KEY = "kMXKut2A1RjE0QMsP28pIzHzo1GRB8yAtyb5zdB0"
}

// Retrofit Service
object WatchmodeService {
    private const val BASE_URL = "https://api.watchmode.com/v1/"

    val api: WatchmodeApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create()) // JSON parser
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // ✅ Add RxJava3 support
            .build()
            .create(WatchmodeApi::class.java)
    }
}
