package by.itacademy.training.travelhelper.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.training.travelhelper.databinding.FragmentVideoListBinding
import by.itacademy.training.travelhelper.model.dto.ItemDto
import by.itacademy.training.travelhelper.ui.adapter.VideoListAdapter
import by.itacademy.training.travelhelper.ui.viewmodel.VideoListViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class VideoListFragment : Fragment(), VideoListAdapter.YoutubePlayerListener {

    private lateinit var binding: FragmentVideoListBinding
    private lateinit var videoListAdapter: VideoListAdapter
    private lateinit var model: VideoListViewModel

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
        setUpAdapter()
        model = ViewModelProvider(this).get(VideoListViewModel::class.java)
        observeVideoList()
    }

    private fun observeVideoList() {
        model.videoList.observe(
            viewLifecycleOwner,
            {
                videoListAdapter.setVideoList(it.items)
            }
        )
    }

    override fun listen(item: ItemDto, youTubePlayerView: YouTubePlayerView) {
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.cueVideo(item.id.videoId, 0f)
            }
        })
    }

    private fun setUpAdapter() {
        videoListAdapter = VideoListAdapter(this)
        binding.recyclerView.apply {
            adapter = videoListAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}
