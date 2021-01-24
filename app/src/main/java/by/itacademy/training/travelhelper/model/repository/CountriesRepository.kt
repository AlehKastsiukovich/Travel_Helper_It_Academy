package by.itacademy.training.travelhelper.model.repository

import by.itacademy.training.travelhelper.domain.Country
import by.itacademy.training.travelhelper.model.dto.CountryDto
import kotlinx.coroutines.flow.Flow

interface CountriesRepository {

    fun getAllCountriesFromDb(): Flow<List<Country>>

    suspend fun insertCountries(countries: List<CountryDto>)

    suspend fun getCountryByName(name: String): Country
}
