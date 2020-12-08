package by.itacademy.training.travelhelper.di.module

import android.content.Context
import androidx.room.Room
import by.itacademy.training.travelhelper.model.db.CountriesRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideCountriesDao(@Named("applicationContext") context: Context): CountriesRoomDatabase {
        return Room.databaseBuilder(
            context,
            CountriesRoomDatabase::class.java,
            ROOM_DATABASE_NAME
        ).build()
    }

    companion object {
        private const val ROOM_DATABASE_NAME = "countries_database"
    }
}
