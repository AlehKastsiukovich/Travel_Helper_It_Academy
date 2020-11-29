package by.itacademy.training.travelhelper.model.api

import by.itacademy.training.travelhelper.entity.VideoList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {

    @GET("search?part=snippet&maxResults=6&key=$YOUTUBE_API_KEY")
    suspend fun getVideos(@Query("q") q: String): VideoList

    companion object {
        private const val YOUTUBE_API_KEY = "AIzaSyD4kOD0D4kBFLLCul5-PKmiyNDhEydo9V4"
        private const val BASE_URL = "https://www.googleapis.com/youtube/v3/"
        private val INSTANCE: YoutubeApi? = null

        fun getYoutubeApi(): YoutubeApi {
            return INSTANCE ?: Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(YoutubeApi::class.java)
        }
    }
}
