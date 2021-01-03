package by.itacademy.training.travelhelper.util

import androidx.room.TypeConverter
import by.itacademy.training.travelhelper.model.dto.RouteDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RouteListConverter @Inject constructor() {

    val gson = Gson()

    @TypeConverter
    fun fromListToString(routes: List<RouteDto>): String = gson.toJson(routes)

    @TypeConverter
    fun fromStringToList(string: String): List<RouteDto> {
        val turnsType = object : TypeToken<List<RouteDto>>() {}.type
        return gson.fromJson(string, turnsType)
    }
}
