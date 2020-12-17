package by.itacademy.training.travelhelper.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.itacademy.training.travelhelper.model.entity.Country
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(
    private val firebase: FirebaseFirestore
) : CountriesRepository {

    override fun getCountriesFromFireStore(): LiveData<List<Country>> {
        val countryList = MutableLiveData<List<Country>>()
        firebase.collection(FIRESTORE_COUNTRY_STORAGE).get()
            .addOnSuccessListener {
                countryList.postValue(it.toObjects(Country::class.java))
            }

        return countryList
    }

    companion object {
        private const val FIRESTORE_COUNTRY_STORAGE = "countries_firestore"
    }
}
