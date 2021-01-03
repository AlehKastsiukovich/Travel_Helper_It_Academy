package by.itacademy.training.travelhelper.di.module

import by.itacademy.training.travelhelper.model.repository.CountriesRepository
import by.itacademy.training.travelhelper.model.repository.CountriesRepositoryImpl
import by.itacademy.training.travelhelper.model.repository.CountryMapper
import by.itacademy.training.travelhelper.model.repository.CountryMapperImpl
import by.itacademy.training.travelhelper.model.repository.DirectionsRepository
import by.itacademy.training.travelhelper.model.repository.DirectionsRepositoryImpl
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

    @Binds
    fun provideCountryMapper(mapper: CountryMapperImpl): CountryMapper

    @Binds
    fun provideDirectionRepository(repositoryImpl: DirectionsRepositoryImpl): DirectionsRepository
}
