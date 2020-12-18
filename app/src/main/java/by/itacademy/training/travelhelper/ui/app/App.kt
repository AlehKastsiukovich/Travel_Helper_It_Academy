package by.itacademy.training.travelhelper.ui.app

import android.app.Application
import by.itacademy.training.travelhelper.di.component.ApplicationComponent
import by.itacademy.training.travelhelper.di.component.DaggerApplicationComponent
import by.itacademy.training.travelhelper.di.module.ApplicationContextModule
import by.itacademy.training.travelhelper.model.dto.CountryDto
import by.itacademy.training.travelhelper.model.repository.CountriesRepository
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

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent
            .builder()
            .applicationContextModule(ApplicationContextModule(this))
            .build()

        inject()

        val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

        firestore.collection(FIRESTORE_COUNTRY_STORAGE).get()
            .addOnSuccessListener {
                val res = it.toObjects(CountryDto::class.java)
                applicationScope.launch { repository.insertCountries(res) }
            }
    }

    private fun inject() {
        appComponent.inject(this)
    }

    companion object {
        private const val FIRESTORE_COUNTRY_STORAGE = "countries_firestore"
    }
}
