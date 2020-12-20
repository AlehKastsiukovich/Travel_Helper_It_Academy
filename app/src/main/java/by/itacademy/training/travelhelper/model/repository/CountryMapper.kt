package by.itacademy.training.travelhelper.model.repository

import by.itacademy.training.travelhelper.model.domain.Country
import by.itacademy.training.travelhelper.model.dto.CountryDto

interface CountryMapper {

    fun mapSingleCountry(countryDto: CountryDto): Country

    fun mapCountryList(list: List<CountryDto>): List<Country>
}
