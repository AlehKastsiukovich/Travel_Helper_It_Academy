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

class CountryDescriptionViewModel(application: Application) : AndroidViewModel(application) {

    @Inject lateinit var repository: CountriesRepository

    var currentCountry: LiveData<Country> = MutableLiveData<Country>()

    init {
        (application as App).appComponent.inject(this)
    }

    fun setCurrentCountry(name: String) {
        currentCountry = liveData {
            val result = repository.getCountryByName(name)
            emit(result)
        }
    }
}
