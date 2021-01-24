package by.itacademy.training.travelhelper.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.itacademy.training.travelhelper.model.dto.CountryDto
import kotlinx.coroutines.flow.Flow

@Dao
interface CountriesDao {

    @Query("SELECT * FROM countries")
    fun getAllCountries(): Flow<List<CountryDto>>

    @Query("DELETE FROM countries")
    suspend fun deleteAllCountries()

    @Query("SELECT * FROM countries WHERE name = :name")
    suspend fun getCountryByName(name: String): CountryDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(countries: List<CountryDto>)
}
