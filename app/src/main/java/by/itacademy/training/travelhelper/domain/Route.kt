package by.itacademy.training.travelhelper.domain

data class Route(
    var title: String,
    var description: String,
    var request: String,
    var imageUrl: String,
    val markers: List<Marker>
)
