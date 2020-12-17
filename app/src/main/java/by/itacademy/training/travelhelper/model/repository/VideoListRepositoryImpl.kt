package by.itacademy.training.travelhelper.model.repository

import by.itacademy.training.travelhelper.model.api.YoutubeApi
import javax.inject.Inject

class VideoListRepositoryImpl @Inject constructor(
    private val api: YoutubeApi
) : VideoListRepository {

    override suspend fun getVideos() = api.getVideos("China travelling")
}
