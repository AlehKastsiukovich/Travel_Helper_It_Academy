package by.itacademy.training.travelhelper.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import by.itacademy.training.travelhelper.model.repository.VideoListRepository
import by.itacademy.training.travelhelper.ui.app.App
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class VideoListViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    @Inject lateinit var repository: VideoListRepository

    init {
        (application as App).appComponent.inject(this)
    }

    val videoList = liveData(Dispatchers.IO) {
        val videoList = repository.getVideos()
        emit(videoList)
    }
}
