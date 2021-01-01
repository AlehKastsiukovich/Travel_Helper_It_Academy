package by.itacademy.training.travelhelper.model.repository

import by.itacademy.training.travelhelper.model.domain.Country
import by.itacademy.training.travelhelper.model.domain.Route
import by.itacademy.training.travelhelper.model.dto.CountryDto
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
        list?.forEach { routs ->
            routeList.add(
                Route(
                    routs.title ?: EMPTY_STRING,
                    routs.description ?: EMPTY_STRING,
                    routs.request ?: EMPTY_STRING,
                    routs.imageUrl ?: EMPTY_STRING
                )
            )
        }
        return routeList
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}
