package by.itacademy.training.travelhelper.model.domain

data class Route(
    var title: String,
    var description: String,
    var request: String,
    var imageUrl: String,
    val markers: List<Marker>
)
