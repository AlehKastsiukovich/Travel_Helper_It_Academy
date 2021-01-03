package by.itacademy.training.travelhelper.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import by.itacademy.training.travelhelper.domain.Country
import by.itacademy.training.travelhelper.domain.Route
import by.itacademy.training.travelhelper.model.dto.maps.DirectionResponse
import by.itacademy.training.travelhelper.model.repository.CountriesRepository
import by.itacademy.training.travelhelper.model.repository.DirectionsRepository
import by.itacademy.training.travelhelper.app.App
import by.itacademy.training.travelhelper.util.Event
import javax.inject.Inject

class CountryDescriptionViewModel(application: Application) : AndroidViewModel(application) {

    @Inject lateinit var repository: CountriesRepository
    @Inject lateinit var directionRepository: DirectionsRepository

    var currentCountry: LiveData<Event<Country>> = MutableLiveData()
    var direction: LiveData<Event<DirectionResponse>> = MutableLiveData()
    private var _route = MutableLiveData<Route>()
    val route: LiveData<Route> = _route

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

    private fun setCurrentDirection(url: String) {
        direction = liveData {
            try {
                val result = directionRepository.getDirection(url)
                emit(Event.success(result))
            } catch (e: Exception) {
                emit(Event.error(null, e.message))
            }
        }
    }

    fun setCurrentRoute(route: Route) {
        setCurrentDirection(route.request)
        _route.value = route
    }
}
