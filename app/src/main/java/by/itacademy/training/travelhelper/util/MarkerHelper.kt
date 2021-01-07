package by.itacademy.training.travelhelper.util

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarkerHelper @Inject constructor() {

    fun splitMarkerText(text: String) = text.split(",")
}
