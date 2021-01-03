package by.itacademy.training.travelhelper.model.repository

import by.itacademy.training.travelhelper.domain.Country
import by.itacademy.training.travelhelper.domain.Marker
import by.itacademy.training.travelhelper.domain.Route
import by.itacademy.training.travelhelper.model.dto.CountryDto
import by.itacademy.training.travelhelper.model.dto.MarkerDto
import by.itacademy.training.travelhelper.model.dto.RouteDto

interface CountryMapper {

    fun mapSingleCountry(countryDto: CountryDto): Country

    fun mapCountryList(list: List<CountryDto>): List<Country>

    fun mapRouteList(list: List<RouteDto>?): List<Route>

    fun mapMarkerList(list: List<MarkerDto>?): List<Marker>
}
