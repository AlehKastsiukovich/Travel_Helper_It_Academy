package by.itacademy.training.travelhelper.ui.app

import android.app.Application
import by.itacademy.training.travelhelper.di.component.ApplicationComponent
import by.itacademy.training.travelhelper.di.component.DaggerApplicationComponent
import by.itacademy.training.travelhelper.di.module.ApplicationContextModule
import by.itacademy.training.travelhelper.model.dto.CountryDto
import by.itacademy.training.travelhelper.model.repository.CountriesRepository
import by.itacademy.training.travelhelper.util.CountryDtoBuilder
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class App : Application() {

    @Inject lateinit var repository: CountriesRepository
    @Inject lateinit var firestore: FirebaseFirestore

    lateinit var appComponent: ApplicationComponent
    lateinit var applicationScope: CoroutineScope

    private val countryDtoBuilder = CountryDtoBuilder()

    override fun onCreate() {
        super.onCreate()

        initApplicationScope()
        initAppComponent()
        inject()
        fetchFireStoreData()
    }

    private fun initAppComponent() {
        appComponent = DaggerApplicationComponent
            .builder()
            .applicationContextModule(ApplicationContextModule(this))
            .build()
    }

    private fun initApplicationScope() {
        applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }

    private fun fetchFireStoreData() {
        val reference = firestore.collection(FIRESTORE_COUNTRY_STORAGE)
            .get()
            .addOnSuccessListener { task ->
                val countries = mutableListOf<CountryDto>()
                task.forEach { document ->
                    val country = document.toObject(CountryDto::class.java)
                    val routs = document.get("routes") as List<Map<String, Any>>
                    countries.add(countryDtoBuilder.buildCountryDto(routs, country))
                }
                sendFireStoreDataToDatabase(countries)
            }
    }

    private fun sendFireStoreDataToDatabase(countries: List<CountryDto>) {
        applicationScope.launch { repository.insertCountries(countries) }
    }

    private fun inject() {
        appComponent.inject(this)
    }

    companion object {
        private const val FIRESTORE_COUNTRY_STORAGE = "countries_firestore"
    }
}
