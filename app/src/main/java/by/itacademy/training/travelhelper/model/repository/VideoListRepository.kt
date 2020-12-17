package by.itacademy.training.travelhelper.model.repository

import by.itacademy.training.travelhelper.model.entity.VideoList

interface VideoListRepository {

    suspend fun getVideos(): VideoList
}
