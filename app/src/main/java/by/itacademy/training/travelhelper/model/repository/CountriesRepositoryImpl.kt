package by.itacademy.training.travelhelper.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.itacademy.training.travelhelper.model.db.CountriesDao
import by.itacademy.training.travelhelper.model.dto.CountryDto
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(
    private val firebase: FirebaseFirestore,
    private val countriesDao: CountriesDao
) : CountriesRepository {

    override fun getCountriesFromFireStore(): LiveData<List<CountryDto>> {
        val countryList = MutableLiveData<List<CountryDto>>()
        firebase.collection(FIRESTORE_COUNTRY_STORAGE).get()
            .addOnSuccessListener {
                countryList.postValue(it.toObjects(CountryDto::class.java))
            }

        return countryList
    }

    override fun getAllCountriesFromDb() = countriesDao.getAllCountries()

    override suspend fun insertCountries(countries: List<CountryDto>) {
        countriesDao.insertCountries(countries)
    }

    companion object {
        private const val FIRESTORE_COUNTRY_STORAGE = "countries_firestore"
    }
}
