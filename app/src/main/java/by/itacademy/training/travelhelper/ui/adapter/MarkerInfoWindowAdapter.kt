package by.itacademy.training.travelhelper.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import by.itacademy.training.travelhelper.R
import by.itacademy.training.travelhelper.util.MarkerHelper
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import javax.inject.Inject

class MarkerInfoWindowAdapter @Inject constructor(
    private val context: Context,
    private val markerHelper: MarkerHelper
) : GoogleMap.InfoWindowAdapter {

    override fun getInfoWindow(marker: Marker?): View? {
        return null
    }

    override fun getInfoContents(marker: Marker?): View {
        return (context as Activity).layoutInflater.inflate(R.layout.marker_info_layout, null)
    }
}
