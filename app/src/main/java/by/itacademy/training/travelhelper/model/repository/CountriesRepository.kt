package by.itacademy.training.travelhelper.model.repository

import androidx.lifecycle.LiveData
import by.itacademy.training.travelhelper.model.domain.Country
import by.itacademy.training.travelhelper.model.dto.CountryDto

interface CountriesRepository {

    fun getAllCountriesFromDb(): LiveData<List<CountryDto>>

    suspend fun insertCountries(countries: List<CountryDto>)

    suspend fun getCountryByName(name: String): Country
}
