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

// interface Dao {
//    fun getContacts():List<Something>
// }
// abstract class Rep(private val dao: Dao) {
//    abstract fun getContacts():List<Something>
// }
// class OtherRepIml(private val dao: Dao) : Rep(dao) {
//    override fun getContacts(): List<Something> {
//        TODO("Not yet implemented")
//    }
// }
// class RxRepImpl(private val dao: Dao) : Rep(dao){
//    override fun getContacts(): List<Something>  {  //тут должен быть observable
//        TODO("Not yet implemented")
//    }
// }
