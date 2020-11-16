package by.itacademy.training.travelhelper.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.itacademy.training.travelhelper.entity.Country
import by.itacademy.training.travelhelper.model.CountriesDao
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CountriesRepository(private val countriesDao: CountriesDao) {

    val countries: LiveData<List<Country>> = countriesDao.getAllCountries()

    fun getCountriesFromFireStore(): LiveData<List<Country>> {
        val database = Firebase.firestore
        val countryList = MutableLiveData<List<Country>>()
        database.collection(FIRESTORE_COUNTRY_STORAGE).get()
            .addOnSuccessListener {
                countryList.postValue(it.toObjects(Country::class.java))
            }
        return countryList
    }

    companion object {
        private const val FIRESTORE_COUNTRY_STORAGE = "countries_firestore"
    }
}
