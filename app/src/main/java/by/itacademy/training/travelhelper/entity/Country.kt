package by.itacademy.training.travelhelper.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val ROOM_COUNTRIES_TABLE_NAME = "countries"

@Entity(tableName = ROOM_COUNTRIES_TABLE_NAME)
data class Country(
    @PrimaryKey val name: String,
    val region: String,
    val description: String,
    val imageUrl: String,
) {
    constructor() : this("Default", "Default", "Default", "Default")
}
