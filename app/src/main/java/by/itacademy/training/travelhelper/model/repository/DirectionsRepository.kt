package by.itacademy.training.travelhelper.model.repository

import by.itacademy.training.travelhelper.model.dto.maps.DirectionResponse

interface DirectionsRepository {

    suspend fun getDirection(url: String): DirectionResponse
}
