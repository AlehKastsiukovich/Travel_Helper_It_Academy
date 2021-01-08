package by.itacademy.training.travelhelper.ui.adapter

import by.itacademy.training.travelhelper.model.dto.ItemDto
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

interface YoutubePlayerListener {

    fun listenCurrentVideo(item: ItemDto, youTubePlayerView: YouTubePlayerView, position: Int)
}
