package by.itacademy.training.travelhelper.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import by.itacademy.training.travelhelper.model.domain.Country
import by.itacademy.training.travelhelper.model.repository.CountriesRepository
import by.itacademy.training.travelhelper.ui.app.App
import by.itacademy.training.travelhelper.util.Event
import javax.inject.Inject

class CountryDescriptionViewModel(application: Application) : AndroidViewModel(application) {

    @Inject lateinit var repository: CountriesRepository

    var currentCountry: LiveData<Event<Country>> = MutableLiveData<Event<Country>>()

    init {
        (application as App).appComponent.inject(this)
    }

    fun setCurrentCountry(name: String) {
        currentCountry = liveData {
            emit(Event.loading(null))
            try {
                val result = repository.getCountryByName(name)
                emit(Event.success(result))
            } catch (e: Exception) {
                emit(Event.error(null, e.message))
            }
        }
    }
}
