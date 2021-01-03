package by.itacademy.training.travelhelper.model.repository

import by.itacademy.training.travelhelper.model.dto.maps.DirectionResponses

interface DirectionsRepository {

    suspend fun getDirection(url: String): DirectionResponses
}
