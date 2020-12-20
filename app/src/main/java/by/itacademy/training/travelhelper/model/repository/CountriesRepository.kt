package by.itacademy.training.travelhelper.model.repository

import by.itacademy.training.travelhelper.model.domain.Country

interface CountriesRepository {

    suspend fun getAllCountriesFromDb(): List<Country>

    suspend fun insertCountries(countries: List<by.itacademy.training.travelhelper.model.dto.CountryDto>)

    suspend fun getCountryByName(name: String): Country
}
