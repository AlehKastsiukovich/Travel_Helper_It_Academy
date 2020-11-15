package by.itacademy.training.travelhelper.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.training.travelhelper.databinding.FragmentCountryListBinding
import by.itacademy.training.travelhelper.entity.Country
import by.itacademy.training.travelhelper.view.adapter.CountryAdapter

class CountryListFragment : Fragment() {

    private lateinit var binding: FragmentCountryListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryListBinding.inflate(inflater, container, false)
        setUpRecyclerView()
        return binding.root
    }

    private fun setUpRecyclerView() {
        val countryAdapter = CountryAdapter()
        val list = listOf(Country(), Country(), Country(), Country(), Country(), Country(), Country())
        countryAdapter.addCountries(list)
        binding.countriesRecyclerView.apply {
            adapter = countryAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}
