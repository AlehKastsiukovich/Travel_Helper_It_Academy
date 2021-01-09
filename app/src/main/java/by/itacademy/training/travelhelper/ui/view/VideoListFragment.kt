package by.itacademy.training.travelhelper.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.training.travelhelper.databinding.FragmentVideoListBinding
import by.itacademy.training.travelhelper.model.dto.ItemDto
import by.itacademy.training.travelhelper.ui.adapter.VideoListAdapter
import by.itacademy.training.travelhelper.ui.adapter.YoutubePlayerListener
import by.itacademy.training.travelhelper.ui.viewmodel.CountryDescriptionViewModel
import by.itacademy.training.travelhelper.ui.viewmodel.VideoListViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import javax.inject.Inject

class VideoListFragment : Fragment(), YoutubePlayerListener {

    @Inject lateinit var videoListAdapter: VideoListAdapter
    @Inject lateinit var countryDescriptionViewModel: CountryDescriptionViewModel
    @Inject lateinit var model: VideoListViewModel

    private lateinit var binding: FragmentVideoListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject()
        setUpAdapter()
        observeVideoList()
        getVideoListByCountryName()
//        addOnBackPressedCallback()
    }

//    private fun addOnBackPressedCallback() {
//        activity?.onBackPressedDispatcher?.addCallback(
//            this,
//            object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    Log.d("TAGG", "addOnBackPressedCallback")
//                    activity?.supportFragmentManager?.popBackStack(null)
//                }
//            }
//        )
//    }

    private fun inject() {
        (activity as CountryActivity)
            .component
            .videoListSubComponentBuilder()
            .with(this)
            .build()
            .inject(this)
    }

    private fun observeVideoList() {
        model.videoList.observe(
            viewLifecycleOwner,
            { videoList ->
                videoList?.let { videoListAdapter.setVideoList(it.items) }
            }
        )
    }

    private fun getVideoListByCountryName() {
        countryDescriptionViewModel.currentCountry.observe(
            viewLifecycleOwner,
            Observer {
                it.data?.name?.let { name -> model.fetchVideosByCountry(name) }
            }
        )
    }

    override fun listenCurrentVideo(
        item: ItemDto,
        youTubePlayerView: YouTubePlayerView,
        position: Int
    ) {
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.cueVideo(item.id.videoId, 0f)
            }
        })
    }

    private fun setUpAdapter() {
        binding.recyclerView.apply {
            adapter = videoListAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}
