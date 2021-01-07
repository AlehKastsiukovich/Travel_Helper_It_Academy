package by.itacademy.training.travelhelper.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.itacademy.training.travelhelper.R
import by.itacademy.training.travelhelper.app.App
import by.itacademy.training.travelhelper.model.dto.VideoListDto
import by.itacademy.training.travelhelper.model.repository.VideoListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class VideoListViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    @Inject lateinit var repository: VideoListRepository
    private val key: String
    private var _videoList = MutableLiveData<VideoListDto>()
    val videoList: LiveData<VideoListDto>
        get() = _videoList

    init {
        (application as App).appComponent.inject(this)
        key = application.resources.getString(R.string.youtube_api_key)
    }

    fun fetchVideosByCountry(countryName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.getVideos("travelling $countryName", key)
                _videoList.postValue(result)
            } catch (e: Exception) {
            }
        }
    }
}
