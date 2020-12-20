package by.itacademy.training.travelhelper.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.training.travelhelper.R
import by.itacademy.training.travelhelper.databinding.ActivityMainBinding
import by.itacademy.training.travelhelper.model.dto.CountryDto
import by.itacademy.training.travelhelper.ui.adapter.CountryAdapter
import by.itacademy.training.travelhelper.ui.adapter.OnCountryItemClickListener
import by.itacademy.training.travelhelper.ui.app.App
import by.itacademy.training.travelhelper.ui.viewmodel.CountriesListViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(), OnCountryItemClickListener {

    @Inject lateinit var countriesListViewModel: CountriesListViewModel
    @Inject lateinit var countryAdapter: CountryAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        inject()
        setContentView(binding.root)

        setUpRecyclerView()
        setUpActionBar()
        observeCountriesList()
    }

    override fun onItemClick(country: CountryDto) {
        val intent = Intent(this, CountryActivity::class.java).apply {
            putExtra(resources.getString(R.string.country_key), country.name)
        }
        startActivity(intent)
    }

    private fun inject() {
        (application as App).appComponent
            .mainActivitySubComponentBuilder()
            .with(this)
            .build()
            .inject(this)
    }

    private fun setUpActionBar() {
        setSupportActionBar(binding.appToolbar)
    }

    private fun setUpRecyclerView() {
        binding.countriesRecyclerView.apply {
            adapter = countryAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun observeCountriesList() {
        countriesListViewModel.countries.observe(
            this, Observer { countryAdapter.addCountries(it) }
        )
    }
}
