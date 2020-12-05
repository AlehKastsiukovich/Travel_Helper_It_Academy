package by.itacademy.training.travelhelper.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.itacademy.training.travelhelper.model.entity.Country

@Database(entities = [Country::class], version = 1, exportSchema = false)
abstract class CountriesRoomDatabase : RoomDatabase() {

    abstract fun countriesDao(): CountriesDao

    companion object {
        private const val ROOM_DATABASE_NAME = "countries_database"
        private val INSTANCE: CountriesRoomDatabase? = null

        fun getDatabase(context: Context): CountriesRoomDatabase {
            return INSTANCE ?: Room.databaseBuilder(
                context,
                CountriesRoomDatabase::class.java,
                ROOM_DATABASE_NAME
            ).build()
        }
    }
}
