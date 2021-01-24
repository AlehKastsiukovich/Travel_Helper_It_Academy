package by.itacademy.training.travelhelper.model.repository

import by.itacademy.training.travelhelper.model.db.CountriesDao
import by.itacademy.training.travelhelper.model.dto.CountryDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountriesRepositoryImpl @Inject constructor(
    private val countriesDao: CountriesDao,
    private val countryMapper: CountryMapper
) : CountriesRepository {

    override fun getAllCountriesFromDb() =
        countriesDao.getAllCountries()
            .map { countryMapper.mapCountryList(it) }
            .flowOn(Dispatchers.IO)

    override suspend fun insertCountries(countries: List<CountryDto>) {
        countriesDao.insertCountries(countries)
    }

    override suspend fun getCountryByName(name: String) = withContext(Dispatchers.IO) {
        countryMapper.mapSingleCountry(countriesDao.getCountryByName(name))
    }

    companion object {
        private const val FIRESTORE_COUNTRY_STORAGE = "countries_firestore"
    }
}
