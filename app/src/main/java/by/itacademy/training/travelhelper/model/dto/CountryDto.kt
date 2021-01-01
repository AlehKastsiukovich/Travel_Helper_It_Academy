package by.itacademy.training.travelhelper.model.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import by.itacademy.training.travelhelper.model.dto.CountryDto.Companion.ROOM_COUNTRIES_TABLE_NAME
import by.itacademy.training.travelhelper.util.RouteListConverter

@Entity(tableName = ROOM_COUNTRIES_TABLE_NAME)
@TypeConverters(RouteListConverter::class)
data class CountryDto(
    @PrimaryKey
    var name: String = EMPTY_STRING,
    var region: String = EMPTY_STRING,
    var description: String = EMPTY_STRING,
    var imageUrl: String = EMPTY_STRING,
    var descriptionImageUrl: String = EMPTY_STRING,
    var capital: String = EMPTY_STRING,
    var language: String = EMPTY_STRING,
    @TypeConverters(RouteListConverter::class)
    var routs: List<RouteDto>? = null
) {

    companion object {
        const val EMPTY_STRING = ""
        const val ROOM_COUNTRIES_TABLE_NAME = "countries"
    }
}
