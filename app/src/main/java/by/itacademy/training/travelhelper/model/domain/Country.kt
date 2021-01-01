package by.itacademy.training.travelhelper.model.domain

data class Country(
    val name: String,
    val region: String,
    val description: String,
    val imageUrl: String,
    val descriptionImageUrl: String,
    val capital: String,
    val language: String,
    val routs: List<Route>
)
