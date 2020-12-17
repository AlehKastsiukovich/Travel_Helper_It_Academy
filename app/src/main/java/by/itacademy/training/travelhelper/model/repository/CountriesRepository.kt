package by.itacademy.training.travelhelper.model.repository

import androidx.lifecycle.LiveData
import by.itacademy.training.travelhelper.model.entity.Country

interface CountriesRepository {

    fun getCountriesFromFireStore(): LiveData<List<Country>>
}
