package by.itacademy.training.travelhelper.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.itacademy.training.travelhelper.model.dto.CountryDto
import by.itacademy.training.travelhelper.model.repository.CountriesRepository
import by.itacademy.training.travelhelper.ui.app.App
import javax.inject.Inject

class CountriesListViewModel(application: Application) : AndroidViewModel(application) {

    @Inject lateinit var repository: CountriesRepository

    val countries: LiveData<List<CountryDto>>
        get() = _countries
    private var _countries: LiveData<List<CountryDto>> = MutableLiveData<List<CountryDto>>()

    init {
        (application as App).appComponent.inject(this)
        fetchCountriesFromDb()
    }

    private fun fetchCountriesFromDb() {
        _countries = repository.getAllCountriesFromDb()
    }
}
