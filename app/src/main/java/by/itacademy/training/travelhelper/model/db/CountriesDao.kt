package by.itacademy.training.travelhelper.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.itacademy.training.travelhelper.model.dto.CountryDto

@Dao
interface CountriesDao {

    @Query("SELECT * FROM countries")
    fun getAllCountries(): LiveData<List<CountryDto>>

    @Query("DELETE FROM countries")
    suspend fun deleteAllCountries()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(countries: List<CountryDto>)
}
