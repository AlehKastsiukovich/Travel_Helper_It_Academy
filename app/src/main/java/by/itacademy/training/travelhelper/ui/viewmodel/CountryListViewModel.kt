package by.itacademy.training.travelhelper.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import by.itacademy.training.travelhelper.model.db.CountriesRoomDatabase
import by.itacademy.training.travelhelper.model.entity.Country
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
