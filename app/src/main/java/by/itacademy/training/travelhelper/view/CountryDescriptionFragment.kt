package by.itacademy.training.travelhelper.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.itacademy.training.travelhelper.databinding.FragmentCountryDescriptionBinding
import by.itacademy.training.travelhelper.entity.Country
import com.bumptech.glide.Glide

class CountryDescriptionFragment : Fragment() {

    private lateinit var binding: FragmentCountryDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryDescriptionBinding.inflate(inflater, container, false)
        val country = getCountryObjectFromCountryListFragment()
        setUpFragmentView(country)
        return binding.root
    }

    private fun setUpFragmentView(country: Country?) {
        Glide.with(this)
            .load(country?.descriptionImageUrl)
            .centerCrop()
            .into(binding.countryDescriptionImage)
        binding.countryText.text = country?.name
        binding.regionText.text = country?.region
        binding.countryDescriptionText.text = country?.description
    }

    private fun getCountryObjectFromCountryListFragment(): Country? {
        return activity?.intent?.extras?.getSerializable(SERIALIZABLE_COUNTRY_OBJECT_EXTRA) as Country
    }
}
