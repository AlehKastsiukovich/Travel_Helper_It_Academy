package by.itacademy.training.travelhelper.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.itacademy.training.travelhelper.model.dto.VideoListDto
import by.itacademy.training.travelhelper.model.repository.VideoListRepository
import by.itacademy.training.travelhelper.ui.app.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class VideoListViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    @Inject lateinit var repository: VideoListRepository
    private var _videoList = MutableLiveData<VideoListDto>()
    val videoList: LiveData<VideoListDto>
        get() = _videoList

    init {
        (application as App).appComponent.inject(this)
    }

    fun fetchVideosByCountry(countryName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _videoList.postValue(repository.getVideos("travelling $countryName"))
        }
    }
}
