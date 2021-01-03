package by.itacademy.training.travelhelper.model.repository

import by.itacademy.training.travelhelper.model.api.MapApiService
import by.itacademy.training.travelhelper.model.dto.maps.DirectionResponses
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DirectionsRepositoryImpl : DirectionsRepository {

    override suspend fun getDirection(url: String): DirectionResponses = withContext(Dispatchers.IO) {
        RetrofitClient.apiServices().getDirection(url)
    }

    private object RetrofitClient {
        fun apiServices(): MapApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://maps.googleapis.com")
                .build()

            return retrofit.create(MapApiService::class.java)
        }
    }
}
