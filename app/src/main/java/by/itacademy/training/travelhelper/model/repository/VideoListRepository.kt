package by.itacademy.training.travelhelper.model.repository

import by.itacademy.training.travelhelper.entity.VideoList
import by.itacademy.training.travelhelper.model.api.YoutubeApi

class VideoListRepository(private val api: YoutubeApi) {

    suspend fun getVideos(): VideoList {
        return api.getVideos("China travelling")
    }
}
