package by.itacademy.training.travelhelper.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.training.travelhelper.databinding.FragmentCountryListBinding
import by.itacademy.training.travelhelper.view.adapter.CountryAdapter
import by.itacademy.training.travelhelper.viewmodel.CountryListViewModel

class CountryListFragment : Fragment() {

    private lateinit var binding: FragmentCountryListBinding
    private lateinit var countryListViewModel: CountryListViewModel
    private lateinit var countryAdapter: CountryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryListBinding.inflate(inflater, container, false)
        setUpRecyclerView()
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        countryListViewModel = ViewModelProvider(this).get(CountryListViewModel::class.java)
    }

    private fun setUpRecyclerView() {
        countryAdapter = CountryAdapter()
        observe()
        binding.countriesRecyclerView.apply {
            adapter = countryAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun observe() {
        countryListViewModel.countries.observe(
            viewLifecycleOwner,
            Observer {
                countryAdapter.addCountries(it)
            }
        )
    }
}
