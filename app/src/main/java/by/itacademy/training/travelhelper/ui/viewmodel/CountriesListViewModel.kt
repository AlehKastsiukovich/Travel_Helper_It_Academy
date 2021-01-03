package by.itacademy.training.travelhelper.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import by.itacademy.training.travelhelper.domain.Country
import by.itacademy.training.travelhelper.model.repository.CountriesRepository
import by.itacademy.training.travelhelper.app.App
import by.itacademy.training.travelhelper.util.Event
import javax.inject.Inject

class CountriesListViewModel(application: Application) : AndroidViewModel(application) {

    @Inject lateinit var repository: CountriesRepository

    val countries: LiveData<Event<List<Country>>>
        get() = _countries
    private var _countries: LiveData<Event<List<Country>>> = MutableLiveData<Event<List<Country>>>()

    init {
        (application as App).appComponent.inject(this)
        fetchCountriesFromDb()
    }

    private fun fetchCountriesFromDb() {
        _countries = liveData {
            emit(Event.loading(null))
            try {
                val result = repository.getAllCountriesFromDb()
                emit(Event.success(result))
            } catch (e: Exception) {
                emit(Event.error(null, e.message))
            }
        }
    }
}
