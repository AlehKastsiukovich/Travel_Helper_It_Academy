package by.itacademy.training.travelhelper.util

import by.itacademy.training.travelhelper.model.dto.CountryDto
import by.itacademy.training.travelhelper.model.dto.MarkerDto
import by.itacademy.training.travelhelper.model.dto.RouteDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryDtoBuilder @Inject constructor() {

    fun buildCountryDto(routeMap: List<Map<String, Any>>, country: CountryDto): CountryDto =
        country.apply {
            routs = buildRouteList(routeMap)
        }

    private fun buildRouteList(routs: List<Map<String, Any>>): List<RouteDto> {
        val routeList = mutableListOf<RouteDto>()
        routs.forEach {
            val markerMap = it["markers"] as List<Map<String, String>>
            val markerList = buildMarkerList(markerMap)
            routeList.add(
                RouteDto(
                    it["title"] as String?,
                    it["description"] as String?,
                    it["request"] as String?,
                    it["imageUrl"] as String?,
                    markerList
                )
            )
        }
        return routeList
    }

    private fun buildMarkerList(markerMap: List<Map<String, String>>): List<MarkerDto> {
        val markerList = mutableListOf<MarkerDto>()
        markerMap.forEach {
            markerList.add(
                MarkerDto(
                    it["title"],
                    it["description"],
                    it["imageUrl"],
                    it["longitude"]?.toDouble(),
                    it["latitude"]?.toDouble()
                )
            )
        }
        return markerList
    }
}
