package by.itacademy.training.travelhelper.model.repository

import androidx.lifecycle.LiveData
import by.itacademy.training.travelhelper.entity.Country
import by.itacademy.training.travelhelper.model.CountriesDao

class CountriesRepository(countriesDao: CountriesDao) {

    val countries: LiveData<List<Country>> = countriesDao.getAllCountries()
}
