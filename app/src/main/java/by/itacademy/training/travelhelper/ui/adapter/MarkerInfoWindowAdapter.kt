package by.itacademy.training.travelhelper.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import by.itacademy.training.travelhelper.R
import by.itacademy.training.travelhelper.databinding.MarkerInfoLayoutBinding
import by.itacademy.training.travelhelper.util.MapPositionHelper
import coil.imageLoader
import coil.request.ImageRequest
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import javax.inject.Inject

class MarkerInfoWindowAdapter @Inject constructor(
    private val context: Context,
    private val markerHelper: MapPositionHelper
) : GoogleMap.InfoWindowAdapter {

    private val view: View = (context as Activity).layoutInflater
        .inflate(R.layout.marker_info_layout, null)
    private var binding: MarkerInfoLayoutBinding

    init {
        binding = MarkerInfoLayoutBinding.bind(view)
    }

    override fun getInfoWindow(marker: Marker?): View? {
        return null
    }

    override fun getInfoContents(marker: Marker?): View {
        val dataPair = markerHelper.splitMarkerText(marker?.title)
        with(binding) {
            markerTitleTextView.text = dataPair.first
            markerDescriptionTextView.text = marker?.snippet
        }
        loadImage(marker, dataPair.second)
        return view
    }

    private fun loadImage(marker: Marker?, imageUrl: String) {
        val request = ImageRequest.Builder(context).apply {
            data(imageUrl)
            allowHardware(false)
            target { drawable ->
                binding.markerImageView.setImageDrawable(drawable)
                markerHelper.prepareMarker(marker)
            }
            error(R.drawable.ic_baseline_image_24_placeholder)
        }.build()
        context.imageLoader.enqueue(request)
    }
}
