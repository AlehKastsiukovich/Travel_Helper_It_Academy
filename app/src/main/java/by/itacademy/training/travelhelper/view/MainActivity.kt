package by.itacademy.training.travelhelper.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.training.travelhelper.databinding.ActivityMainBinding
import by.itacademy.training.travelhelper.entity.Country
import by.itacademy.training.travelhelper.view.adapter.CountryAdapter
import by.itacademy.training.travelhelper.viewmodel.CountryListViewModel

const val SERIALIZABLE_COUNTRY_OBJECT_EXTRA = "serializableCountry"

class MainActivity : AppCompatActivity(), CountryAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var countryListViewModel: CountryListViewModel
    private lateinit var countryAdapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        countryListViewModel = ViewModelProvider(this).get(CountryListViewModel::class.java)

        setUpRecyclerView()
        setUpActionBar()
    }

    override fun onItemClick(country: Country) {
        val intent = Intent(this, CountryActivity::class.java)
        intent.putExtra(SERIALIZABLE_COUNTRY_OBJECT_EXTRA, country)
        startActivity(intent)
    }

    private fun setUpActionBar() {
        setSupportActionBar(binding.appToolbar)
    }

    private fun setUpRecyclerView() {
        countryAdapter = CountryAdapter(this)
        observe()
        binding.countriesRecyclerView.apply {
            adapter = countryAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun observe() {
        countryListViewModel.countries.observe(
            this,
            Observer {
                countryAdapter.addCountries(it)
            }
        )
    }
}
