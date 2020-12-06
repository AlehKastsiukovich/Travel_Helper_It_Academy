package by.itacademy.training.travelhelper.model.api

import by.itacademy.training.travelhelper.model.entity.VideoList
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {

    @GET("search?part=snippet&maxResults=6&key=$YOUTUBE_API_KEY")
    suspend fun getVideos(@Query("q") q: String): VideoList

    companion object {
        private const val YOUTUBE_API_KEY = "AIzaSyD4kOD0D4kBFLLCul5-PKmiyNDhEydo9V4"
    }
}
