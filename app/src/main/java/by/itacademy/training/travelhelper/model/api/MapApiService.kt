package by.itacademy.training.travelhelper.model.api

import by.itacademy.training.travelhelper.model.dto.maps.DirectionResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface MapApiService {
    @GET
    suspend fun getDirection(@Url url: String): DirectionResponse
}
