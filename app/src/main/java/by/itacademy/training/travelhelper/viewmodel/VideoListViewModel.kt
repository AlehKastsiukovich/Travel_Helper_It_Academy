package by.itacademy.training.travelhelper.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import by.itacademy.training.travelhelper.model.api.YoutubeApi
import by.itacademy.training.travelhelper.model.repository.VideoListRepository
import kotlinx.coroutines.Dispatchers

class VideoListViewModel : ViewModel() {

    private val api: YoutubeApi = YoutubeApi.getYoutubeApi()
    private val repository: VideoListRepository = VideoListRepository(api)

    val videoList = liveData(Dispatchers.IO) {
        val videoList = repository.getVideos()
        emit(videoList)
    }
}
