package by.itacademy.training.travelhelper.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.itacademy.training.travelhelper.R
import by.itacademy.training.travelhelper.databinding.FragmentCountryDescriptionBinding
import by.itacademy.training.travelhelper.model.dto.CountryDto
import by.itacademy.training.travelhelper.model.repository.CountriesRepository
import by.itacademy.training.travelhelper.ui.app.App
import com.bumptech.glide.Glide
import javax.inject.Inject

class CountryDescriptionFragment : Fragment() {

    private lateinit var binding: FragmentCountryDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val country = getCountryObjectFromCountryListFragment()
        setUpFragmentView(country)
    }

    private fun inject() {
        activity?.run {
            (application as App)
                .appComponent
                .countryActivitySubComponentBuilder().with(this)
        }
    }

    private fun setUpFragmentView(country: CountryDto?) {
        Glide.with(this)
            .load(country?.descriptionImageUrl)
            .centerCrop()
            .into(binding.countryDescriptionImage)
        with(binding) {
            countryText.text = country?.name
            regionText.text = country?.region
            capitalTextView.text = country?.capital
            countryLanguageTextView.text = country?.language
            countryDescriptionText.text = country?.description
        }
    }

    private fun getCountryObjectFromCountryListFragment(): CountryDto? {
        val name = activity?.
        intent?.
        extras?.
        getString(resources.getString(R.string.country_key))

        return
    }
}
