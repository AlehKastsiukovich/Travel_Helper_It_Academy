package by.itacademy.training.travelhelper.entity

import androidx.room.Entity

@Entity(tableName = "countries")
data class Country(
    val name: String = "Country",
    val location: String = "Europe",
    val description: String = "No",
    val imageUrl: String = "",
    val population: Int = 10
)
