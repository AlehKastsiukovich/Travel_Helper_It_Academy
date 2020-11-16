package by.itacademy.training.travelhelper.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import by.itacademy.training.travelhelper.entity.Country
import by.itacademy.training.travelhelper.model.CountriesRoomDatabase
import by.itacademy.training.travelhelper.model.repository.CountriesRepository

class CountryListViewModel(application: Application) : AndroidViewModel(application) {

    val countries: LiveData<List<Country>>
    private val repository: CountriesRepository

    init {
        val dao = CountriesRoomDatabase.getDatabase(application).countriesDao()
        repository = CountriesRepository(dao)
        countries = repository.getCountriesFromFireStore()
    }
}
