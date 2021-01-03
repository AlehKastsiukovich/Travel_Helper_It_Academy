package by.itacademy.training.travelhelper.model.api

import by.itacademy.training.travelhelper.model.dto.VideoListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {

    @GET("search?part=snippet&maxResults=6")
    suspend fun getVideos(
        @Query("q") q: String,
        @Query("key") key: String
    ): VideoListDto
}
