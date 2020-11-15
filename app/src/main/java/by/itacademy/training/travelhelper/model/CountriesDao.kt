package by.itacademy.training.travelhelper.model

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CountriesDao {

    @Query("SELECT * FROM countries")
    fun getAllCountries()

    @Query("DELETE FROM countries")
    fun deleteAllCountries()
}
