package by.itacademy.training.travelhelper.model.repository

import by.itacademy.training.travelhelper.model.domain.Country
import by.itacademy.training.travelhelper.model.dto.CountryDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryMapperImpl @Inject constructor() : CountryMapper {

    override fun map(countryDto: CountryDto) = countryDto.run {
        Country(
            name,
            region,
            description,
            imageUrl,
            descriptionImageUrl,
            capital,
            language
        )
    }
}
