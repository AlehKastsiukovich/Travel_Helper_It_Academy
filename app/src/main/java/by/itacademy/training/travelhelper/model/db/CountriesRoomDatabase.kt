package by.itacademy.training.travelhelper.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import by.itacademy.training.travelhelper.model.dto.CountryDto

@Database(entities = [CountryDto::class], version = 1, exportSchema = false)
abstract class CountriesRoomDatabase : RoomDatabase() {

    abstract fun countriesDao(): CountriesDao
}
