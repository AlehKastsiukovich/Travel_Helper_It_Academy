package by.itacademy.training.travelhelper.model.repository

import by.itacademy.training.travelhelper.model.api.YoutubeApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VideoListRepositoryImpl @Inject constructor(
    private val api: YoutubeApi
) : VideoListRepository {

    override suspend fun getVideos(countryName: String, key: String) = api.getVideos(countryName, key)
}
