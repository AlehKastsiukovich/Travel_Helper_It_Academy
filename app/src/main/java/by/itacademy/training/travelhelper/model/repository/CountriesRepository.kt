package by.itacademy.training.travelhelper.model.repository

import by.itacademy.training.travelhelper.model.domain.Country
import by.itacademy.training.travelhelper.model.dto.CountryDto

interface CountriesRepository {

    suspend fun getAllCountriesFromDb(): List<Country>

    suspend fun insertCountries(countries: List<CountryDto>)

    suspend fun getCountryByName(name: String): Country
}
