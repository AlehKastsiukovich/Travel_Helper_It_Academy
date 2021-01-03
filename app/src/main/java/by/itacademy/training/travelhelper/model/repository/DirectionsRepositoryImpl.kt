package by.itacademy.training.travelhelper.model.repository

import by.itacademy.training.travelhelper.model.api.MapsApiService
import by.itacademy.training.travelhelper.model.dto.maps.DirectionResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DirectionsRepositoryImpl @Inject constructor(
    private val api: MapsApiService
) : DirectionsRepository {

    override suspend fun getDirection(url: String): DirectionResponse = withContext(Dispatchers.IO) {
        api.getDirection(url)
    }
}
