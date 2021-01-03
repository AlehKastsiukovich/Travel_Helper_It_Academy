package by.itacademy.training.travelhelper.model.api

import by.itacademy.training.travelhelper.model.dto.maps.DirectionResponses
import retrofit2.http.GET
import retrofit2.http.Url

interface MapApiService {
    @GET
    suspend fun getDirection(@Url url: String): DirectionResponses
}
