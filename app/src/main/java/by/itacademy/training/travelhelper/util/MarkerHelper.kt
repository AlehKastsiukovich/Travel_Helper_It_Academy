package by.itacademy.training.travelhelper.util

import com.google.android.gms.maps.model.Marker
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarkerHelper @Inject constructor() {

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

    companion object {
        private const val TITLE_POSITION = 0
        private const val IMAGE_URL_POSITION = 1
        private const val TEXT_SPLITTER = ","
        private const val EMPTY_STRING = ""
    }
}
