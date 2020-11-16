package by.itacademy.training.travelhelper.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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
        Glide.with(this)
            .load(country.descriptionImageUrl)
            .centerCrop()
            .into(binding.countryDescriptionImage)
        binding.countryText.text = country.name
        binding.regionText.text = country.region
        binding.countryDescriptionText.text = country.description
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            activity?.onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getCountryObjectFromCountryListFragment(): Country {
        return arguments?.getSerializable(SERIALIZABLE_COUNTRY_OBJECT_KEY) as Country
    }
}
