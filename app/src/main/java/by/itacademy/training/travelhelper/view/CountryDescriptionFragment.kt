package by.itacademy.training.travelhelper.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.itacademy.training.travelhelper.R
import by.itacademy.training.travelhelper.databinding.FragmentCountryDescriptionBinding
import by.itacademy.training.travelhelper.entity.Country
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView

class CountryDescriptionFragment : Fragment() {

    private lateinit var binding: FragmentCountryDescriptionBinding
    private lateinit var currentFragment: Fragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryDescriptionBinding.inflate(inflater, container, false)
        activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationBar)?.visibility = View.VISIBLE
        val country = getCountryObjectFromCountryListFragment()
        setUpFragmentView(country)
        initBottomToolbarFragments()
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
        (activity as MainActivity).apply {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
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
        return Country()
    }

    private fun initBottomToolbarFragments() {
        val countryDescriptionFragment = this
        val videoListFragment = VideoListFragment()
        val routeListFragment = RouteListFragment()
        currentFragment = countryDescriptionFragment

        activity?.supportFragmentManager?.beginTransaction()?.run {
            add(R.id.fragmentsContainer, routeListFragment).hide(routeListFragment).addToBackStack(null)
            add(R.id.fragmentsContainer, videoListFragment).hide(videoListFragment).addToBackStack(null)
            commit()
        }

        activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationBar)?.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.countryDescription -> setCurrentFragment(countryDescriptionFragment)
                R.id.countryInfoVideoList -> setCurrentFragment(videoListFragment)
                R.id.routes -> setCurrentFragment(routeListFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(openFragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()?.run {
            hide(currentFragment)
            show(openFragment)
            commit()
        }
        currentFragment = openFragment
    }
}
