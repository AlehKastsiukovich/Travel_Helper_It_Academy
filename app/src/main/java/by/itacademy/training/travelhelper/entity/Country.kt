package by.itacademy.training.travelhelper.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

const val ROOM_COUNTRIES_TABLE_NAME = "countries"

@Entity(tableName = ROOM_COUNTRIES_TABLE_NAME)
data class Country(
    @PrimaryKey val name: String,
    val region: String,
    val description: String,
    val imageUrl: String,
    val descriptionImageUrl: String,
    val capital: String,
    val language: String
) : Serializable {
    constructor() : this(
        "Default",
        "Default",
        "Default",
        "Default",
        "Default",
        "Default",
        "Default"
    )
}
