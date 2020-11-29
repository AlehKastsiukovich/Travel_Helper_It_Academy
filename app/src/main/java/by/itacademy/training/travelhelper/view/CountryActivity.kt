package by.itacademy.training.travelhelper.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.itacademy.training.travelhelper.R
import by.itacademy.training.travelhelper.databinding.ActivityCountryBinding

class CountryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountryBinding
    private lateinit var currentFragment: Fragment
    private lateinit var countryDescriptionFragment: CountryDescriptionFragment
    private lateinit var videoListFragment: VideoListFragment
    private lateinit var routeListFragment: RouteListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpFragments()
        onNavigationBarItemSelected()
        setUpActionBar()
    }

    private fun setUpFragments() {
        countryDescriptionFragment = CountryDescriptionFragment()
        videoListFragment = VideoListFragment()
        routeListFragment = RouteListFragment()
        currentFragment = countryDescriptionFragment

        supportFragmentManager.beginTransaction().run {
            add(R.id.fragmentsContainer, countryDescriptionFragment)
            add(R.id.fragmentsContainer, routeListFragment).hide(routeListFragment)
            add(R.id.fragmentsContainer, videoListFragment).hide(videoListFragment)
            commit()
        }
    }

    private fun setUpActionBar() {
        setSupportActionBar(binding.appToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun onNavigationBarItemSelected() {
        binding.bottomNavigationBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.countryDescription -> setCurrentFragment(countryDescriptionFragment)
                R.id.countryInfoVideoList -> setCurrentFragment(videoListFragment)
                R.id.routes -> setCurrentFragment(routeListFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(openFragment: Fragment) {
        supportFragmentManager.beginTransaction().run {
            hide(currentFragment)
            show(openFragment)
            commit()
        }
        currentFragment = openFragment
    }
}
