package by.itacademy.training.travelhelper.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import by.itacademy.training.travelhelper.model.entity.Country

@Dao
interface CountriesDao {

    @Query("SELECT * FROM countries")
    fun getAllCountries(): LiveData<List<Country>>

    @Query("DELETE FROM countries")
    fun deleteAllCountries()
}
