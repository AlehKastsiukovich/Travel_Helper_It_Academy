package by.itacademy.training.travelhelper.model.repository

import by.itacademy.training.travelhelper.model.domain.Country
import by.itacademy.training.travelhelper.model.dto.CountryDto

class CountryMapperImpl : CountryMapper {

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
