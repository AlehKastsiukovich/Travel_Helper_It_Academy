package by.itacademy.training.travelhelper.model.repository

import by.itacademy.training.travelhelper.model.api.YoutubeApi
import javax.inject.Inject

class VideoListRepository @Inject constructor(private val api: YoutubeApi) {

    suspend fun getVideos() = api.getVideos("China travelling")
}
