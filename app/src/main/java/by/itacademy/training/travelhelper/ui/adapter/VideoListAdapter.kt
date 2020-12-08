package by.itacademy.training.travelhelper.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.training.travelhelper.R
import by.itacademy.training.travelhelper.databinding.ItemVideoBinding
import by.itacademy.training.travelhelper.model.entity.Item
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class VideoListAdapter(private val youtubePlayerListener: YoutubePlayerListener) :
    RecyclerView.Adapter<VideoListAdapter.VideoListViewHolder>() {

    private var videoList = listOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoListViewHolder {
        return VideoListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false),
            youtubePlayerListener
        )
    }

    override fun onBindViewHolder(holder: VideoListViewHolder, position: Int) {
        holder.bind(videoList[position])
    }

    override fun getItemCount() = videoList.size

    fun setVideoList(videoList: List<Item>) {
        this.videoList = videoList
        notifyDataSetChanged()
    }

    class VideoListViewHolder(
        itemView: View,
        private val youtubePlayerListener: YoutubePlayerListener
    ) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemVideoBinding.bind(itemView)
        private val player = binding.youtubePlayer
        private val title = binding.videoTitleTextView

        fun bind(item: Item) {
            youtubePlayerListener.listen(item, player)
            title.text = item.snippet.title
        }
    }

    interface YoutubePlayerListener {
        fun listen(item: Item, youTubePlayerView: YouTubePlayerView)
    }
}
