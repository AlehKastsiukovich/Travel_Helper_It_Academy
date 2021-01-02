package by.itacademy.training.travelhelper.model.dto

data class MarkerDto(
    private var title: String? = null,
    private var description: String? = null,
    private var imageUrl: String? = null,
    private var longitude: Double? = null,
    private var latitude: Double? = null
)
