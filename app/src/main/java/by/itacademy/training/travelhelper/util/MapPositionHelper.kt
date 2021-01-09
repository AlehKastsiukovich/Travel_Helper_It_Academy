package by.itacademy.training.travelhelper.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapPositionHelper @Inject constructor() {

    fun splitMarkerText(text: String?): Pair<String, String> {
        val dataList = text?.run {
            split(TEXT_SPLITTER)
        } ?: mutableListOf(EMPTY_STRING, EMPTY_STRING)
        val title = dataList[TITLE_POSITION]
        val imageUrl = dataList[IMAGE_URL_POSITION]
        return Pair(title, imageUrl)
    }

    fun prepareMarker(marker: Marker?) {
        if (marker != null && marker.isInfoWindowShown) {
            with(marker) {
                hideInfoWindow()
                showInfoWindow()
            }
        }
    }

    fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(BOUND_POSITION, BOUND_POSITION, intrinsicWidth, intrinsicHeight)
            val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }

    fun createLocationRequest(): LocationRequest =
        LocationRequest.create().apply {
            interval = LOCATION_REQUEST_INTERVAL
            fastestInterval = LOCATION_FASTEST_REQUEST_INTERVAL
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

    companion object {
        private const val LOCATION_REQUEST_INTERVAL: Long = 60000
        private const val LOCATION_FASTEST_REQUEST_INTERVAL: Long = 5000
        private const val BOUND_POSITION = 0
        private const val TITLE_POSITION = 0
        private const val IMAGE_URL_POSITION = 1
        private const val TEXT_SPLITTER = ","
        private const val EMPTY_STRING = ""
    }
}
