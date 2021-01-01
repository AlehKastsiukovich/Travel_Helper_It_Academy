package by.itacademy.training.travelhelper.model.repository

import by.itacademy.training.travelhelper.model.domain.Country
import by.itacademy.training.travelhelper.model.dto.CountryDto
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
            language ?: EMPTY_STRING
        )
    }

    override fun mapCountryList(list: List<CountryDto>): List<Country> {
        val countries = mutableListOf<Country>()
        list.forEach {
            countries.add(mapSingleCountry(it))
        }
        return countries
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}
