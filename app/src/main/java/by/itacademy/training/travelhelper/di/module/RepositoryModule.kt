package by.itacademy.training.travelhelper.di.module

import by.itacademy.training.travelhelper.model.api.YoutubeApi
import by.itacademy.training.travelhelper.model.db.CountriesRoomDatabase
import by.itacademy.training.travelhelper.model.repository.CountriesRepository
import by.itacademy.training.travelhelper.model.repository.VideoListRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideVideoListRepository(api: YoutubeApi) = VideoListRepository(api)

    @Singleton
    @Provides
    fun provideCountriesRepository(database: CountriesRoomDatabase): CountriesRepository {
        return CountriesRepository(database.countriesDao())
    }
}
