package by.itacademy.training.travelhelper.model.repository

import by.itacademy.training.travelhelper.model.dto.VideoListDto

interface VideoListRepository {

    suspend fun getVideos(countryName: String): VideoListDto
}
