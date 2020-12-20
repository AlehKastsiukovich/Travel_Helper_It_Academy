package by.itacademy.training.travelhelper.model.repository

import androidx.lifecycle.LiveData
import by.itacademy.training.travelhelper.model.dto.CountryDto

interface CountriesRepository {

    fun getCountriesFromFireStore(): LiveData<List<CountryDto>>

    fun getAllCountriesFromDb(): LiveData<List<CountryDto>>

    suspend fun insertCountries(countries: List<CountryDto>)

    suspend fun getCountryByName(name: String): CountryDto
}
