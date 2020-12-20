package by.itacademy.training.travelhelper.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import by.itacademy.training.travelhelper.model.domain.Country
import by.itacademy.training.travelhelper.model.repository.CountriesRepository
import by.itacademy.training.travelhelper.ui.app.App
import javax.inject.Inject

class CountriesListViewModel(application: Application) : AndroidViewModel(application) {

    @Inject lateinit var repository: CountriesRepository

    val countries: LiveData<List<Country>>
        get() = _countries
    private var _countries: LiveData<List<Country>> = MutableLiveData<List<Country>>()

    init {
        (application as App).appComponent.inject(this)
        fetchCountriesFromDb()
    }

    private fun fetchCountriesFromDb() {
        _countries = liveData {
            val result = repository.getAllCountriesFromDb()
            emit(result)
        }
    }
}
