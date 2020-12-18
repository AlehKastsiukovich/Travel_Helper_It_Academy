package by.itacademy.training.travelhelper.model.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import by.itacademy.training.travelhelper.model.dto.CountryDto.Companion.ROOM_COUNTRIES_TABLE_NAME

@Entity(tableName = ROOM_COUNTRIES_TABLE_NAME)
data class CountryDto(
    @PrimaryKey var name: String = DEFAULT_EMPTY_STRING,
    var region: String = DEFAULT_EMPTY_STRING,
    var description: String = DEFAULT_EMPTY_STRING,
    var imageUrl: String = DEFAULT_EMPTY_STRING,
    var descriptionImageUrl: String = DEFAULT_EMPTY_STRING,
    var capital: String = DEFAULT_EMPTY_STRING,
    var language: String = DEFAULT_EMPTY_STRING
) {

    companion object {
        const val ROOM_COUNTRIES_TABLE_NAME = "countries"
        private const val DEFAULT_EMPTY_STRING = ""
    }
}
