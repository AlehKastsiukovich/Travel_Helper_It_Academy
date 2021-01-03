package by.itacademy.training.travelhelper.di.module

import by.itacademy.training.travelhelper.model.api.MapsApiService
import by.itacademy.training.travelhelper.model.api.YoutubeApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Named(YOUTUBE_PROVIDE_NAME)
    @Provides
    fun provideRetrofitForYoutube(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(YOUTUBE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Named(MAPS_PROVIDE_NAME)
    @Provides
    fun provideRetrofitForMaps(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(MAPS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideYoutubeApi(@Named(YOUTUBE_PROVIDE_NAME)retrofit: Retrofit): YoutubeApi =
        retrofit.create(YoutubeApi::class.java)

    @Singleton
    @Provides
    fun provideMapsApiService(@Named(MAPS_PROVIDE_NAME)retrofit: Retrofit): MapsApiService =
        retrofit.create(MapsApiService::class.java)

    companion object {
        private const val YOUTUBE_PROVIDE_NAME = "YOUTUBE"
        private const val MAPS_PROVIDE_NAME = "MAPS"
        private const val YOUTUBE_BASE_URL = "https://www.googleapis.com/youtube/v3/"
        private const val MAPS_BASE_URL = "https://maps.googleapis.com"
    }
}
