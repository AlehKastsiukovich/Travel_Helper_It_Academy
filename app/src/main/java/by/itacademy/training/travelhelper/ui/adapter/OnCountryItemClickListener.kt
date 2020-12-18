package by.itacademy.training.travelhelper.ui.adapter

import by.itacademy.training.travelhelper.model.dto.CountryDto

interface OnCountryItemClickListener {
    fun onItemClick(country: CountryDto)
}
