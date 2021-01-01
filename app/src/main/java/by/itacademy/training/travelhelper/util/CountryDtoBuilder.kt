package by.itacademy.training.travelhelper.util

import by.itacademy.training.travelhelper.model.dto.CountryDto
import by.itacademy.training.travelhelper.model.dto.RouteDto

class CountryDtoBuilder {

    private fun buildRouteList(routs: List<Map<String, String>>): List<RouteDto> {
        val routeList = mutableListOf<RouteDto>()
        routs.forEach {
            routeList.add(
                RouteDto(
                    it["title"],
                    it["description"],
                    it["request"],
                    it["imageUrl"]
                )
            )
        }
        return routeList
    }

    fun buildCountryDto(routeMap: List<Map<String, String>>, country: CountryDto): CountryDto =
        country.apply {
            routs = buildRouteList(routeMap)
        }
}
