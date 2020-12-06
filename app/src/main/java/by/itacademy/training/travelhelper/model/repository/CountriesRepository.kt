package by.itacademy.training.travelhelper.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.itacademy.training.travelhelper.model.db.CountriesDao
import by.itacademy.training.travelhelper.model.entity.Country
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class CountriesRepository @Inject constructor(private val countriesDao: CountriesDao) {

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
