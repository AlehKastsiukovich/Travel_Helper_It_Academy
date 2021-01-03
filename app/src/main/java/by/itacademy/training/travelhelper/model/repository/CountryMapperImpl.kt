package by.itacademy.training.travelhelper.model.repository

import by.itacademy.training.travelhelper.domain.Country
import by.itacademy.training.travelhelper.domain.Marker
import by.itacademy.training.travelhelper.domain.Route
import by.itacademy.training.travelhelper.model.dto.CountryDto
import by.itacademy.training.travelhelper.model.dto.MarkerDto
import by.itacademy.training.travelhelper.model.dto.RouteDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryMapperImpl @Inject constructor() : CountryMapper {

    override fun mapSingleCountry(countryDto: CountryDto) = countryDto.run {
        Country(
            name,
            region ?: EMPTY_STRING,
            description ?: EMPTY_STRING,
            imageUrl ?: EMPTY_STRING,
            descriptionImageUrl ?: EMPTY_STRING,
            capital ?: EMPTY_STRING,
            language ?: EMPTY_STRING,
            mapRouteList(routs)
        )
    }

    override fun mapCountryList(list: List<CountryDto>): List<Country> {
        val countries = mutableListOf<Country>()
        list.forEach {
            countries.add(mapSingleCountry(it))
        }
        return countries
    }

    override fun mapRouteList(list: List<RouteDto>?): List<Route> {
        val routeList = mutableListOf<Route>()
        list?.forEach { route ->
            val markerList = mapMarkerList(route.markers)
            routeList.add(
                Route(
                    route.title ?: EMPTY_STRING,
                    route.description ?: EMPTY_STRING,
                    route.request ?: EMPTY_STRING,
                    route.imageUrl ?: EMPTY_STRING,
                    markerList
                )
            )
        }
        return routeList
    }

    override fun mapMarkerList(list: List<MarkerDto>?): List<Marker> {
        val markerList = mutableListOf<Marker>()
        list?.forEach { marker ->
            markerList.add(
                Marker(
                    marker.title ?: EMPTY_STRING,
                    marker.description ?: EMPTY_STRING,
                    marker.imageUrl ?: EMPTY_STRING,
                    marker.longitude ?: DEFAULT_LONGITUDE,
                    marker.latitude ?: DEFAULT_LATITUDE
                )
            )
        }
        return markerList
    }

    companion object {
        private const val EMPTY_STRING = ""
        private const val DEFAULT_LONGITUDE = 53.893009
        private const val DEFAULT_LATITUDE = 27.567444
    }
}
