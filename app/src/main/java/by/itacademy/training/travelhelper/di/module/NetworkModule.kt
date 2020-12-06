package by.itacademy.training.travelhelper.di.module

import by.itacademy.training.travelhelper.model.api.YoutubeApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideYoutubeApi(retrofit: Retrofit): YoutubeApi = retrofit.create(YoutubeApi::class.java)

    companion object {
        private const val BASE_URL = "https://www.googleapis.com/youtube/v3/"
    }
}
