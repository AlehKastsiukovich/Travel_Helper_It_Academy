package by.itacademy.training.travelhelper.di.module

import by.itacademy.training.travelhelper.model.repository.CountriesRepository
import by.itacademy.training.travelhelper.model.repository.CountriesRepositoryImpl
import by.itacademy.training.travelhelper.model.repository.VideoListRepository
import by.itacademy.training.travelhelper.model.repository.VideoListRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun provideCountriesRepository(repository: CountriesRepositoryImpl): CountriesRepository

    @Binds
    fun provideVideoListRepository(repository: VideoListRepositoryImpl): VideoListRepository
}
