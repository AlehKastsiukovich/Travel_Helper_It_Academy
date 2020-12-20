package by.itacademy.training.travelhelper.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.training.travelhelper.R
import by.itacademy.training.travelhelper.databinding.ItemVideoBinding
import by.itacademy.training.travelhelper.di.component.VideoListFragmentScope
import by.itacademy.training.travelhelper.model.dto.ItemDto
import javax.inject.Inject

@VideoListFragmentScope
class VideoListAdapter @Inject constructor(
    private val youtubePlayerListener: YoutubePlayerListener
) : RecyclerView.Adapter<VideoListAdapter.VideoListViewHolder>() {

    private var videoList = listOf<ItemDto>()

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

    fun setVideoList(videoList: List<ItemDto>) {
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

        fun bind(item: ItemDto) {
            youtubePlayerListener.listen(item, player)
            title.text = item.snippet.title
        }
    }
}
