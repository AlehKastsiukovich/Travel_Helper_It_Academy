package by.itacademy.training.travelhelper.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.itacademy.training.travelhelper.model.entity.Country
import by.itacademy.training.travelhelper.model.repository.CountriesRepository
import by.itacademy.training.travelhelper.ui.app.App
import javax.inject.Inject

class CountryListViewModel(application: Application) : AndroidViewModel(application) {

    @Inject lateinit var repository: CountriesRepository

    val countries: LiveData<List<Country>>
        get() = _countries
    private var _countries = MutableLiveData<List<Country>>()

    init {
        (application as App).appComponent.inject(this)
        _countries = repository.getCountriesFromFireStore() as MutableLiveData<List<Country>>
    }
}
