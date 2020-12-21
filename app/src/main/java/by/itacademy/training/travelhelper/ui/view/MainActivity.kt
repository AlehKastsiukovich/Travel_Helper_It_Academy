package by.itacademy.training.travelhelper.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.training.travelhelper.R
import by.itacademy.training.travelhelper.databinding.ActivityMainBinding
import by.itacademy.training.travelhelper.model.domain.Country
import by.itacademy.training.travelhelper.ui.adapter.CountryAdapter
import by.itacademy.training.travelhelper.ui.adapter.OnCountryItemClickListener
import by.itacademy.training.travelhelper.ui.app.App
import by.itacademy.training.travelhelper.ui.viewmodel.CountriesListViewModel
import by.itacademy.training.travelhelper.util.Status
import com.google.android.material.snackbar.Snackbar
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

    override fun onItemClick(country: Country) {
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
            this,
            Observer {
                when (it.status) {
                    Status.LOADING -> viewProgressBar()
                    Status.SUCCESS -> it.data?.let { countries -> viewCountryList(countries) }
                    Status.ERROR -> it.message?.let { message -> viewError(message) }
                }
            }
        )
    }

    private fun viewCountryList(countries: List<Country>) {
        setSuccessDataToAdapter(countries)
        binding.progressBar.visibility = View.INVISIBLE
        binding.mainLayout.visibility = View.VISIBLE
    }

    private fun viewProgressBar() {
        binding.mainLayout.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun viewError(message: String) {
        binding.progressBar.visibility = View.VISIBLE
        showErrorMessage(message)
    }

    private fun showErrorMessage(message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun setSuccessDataToAdapter(countries: List<Country>) {
        countryAdapter.addCountries(countries)
    }
}
