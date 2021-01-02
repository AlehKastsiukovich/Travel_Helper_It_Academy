package by.itacademy.training.travelhelper.model.dto

data class RouteDto(
    var title: String? = null,
    var description: String? = null,
    var request: String? = null,
    var imageUrl: String? = null,
    var markers: List<MarkerDto>? = null
)
