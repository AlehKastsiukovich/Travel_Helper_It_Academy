package by.itacademy.training.travelhelper.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.training.travelhelper.databinding.FragmentVideoListBinding
import by.itacademy.training.travelhelper.entity.Item
import by.itacademy.training.travelhelper.view.adapter.VideoListAdapter
import by.itacademy.training.travelhelper.viewmodel.VideoListViewModel
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
        setUpAdapter()
        model.videoList.observe(
            viewLifecycleOwner,
            {
                videoListAdapter.setVideoList(it.items)
            }
        )
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this).get(VideoListViewModel::class.java)
    }

    override fun listen(item: Item, youTubePlayerView: YouTubePlayerView) {
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
