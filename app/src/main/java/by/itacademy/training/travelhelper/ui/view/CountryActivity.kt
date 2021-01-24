package by.itacademy.training.travelhelper.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import by.itacademy.training.travelhelper.R
import by.itacademy.training.travelhelper.app.App
import by.itacademy.training.travelhelper.di.component.CountryActivityComponent
import by.itacademy.training.travelhelper.ui.viewmodel.CountryDescriptionViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class CountryActivity : AppCompatActivity() {

    @Inject lateinit var model: CountryDescriptionViewModel

    lateinit var component: CountryActivityComponent
    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponent()
        inject()
        setContentView(R.layout.activity_country)
        initNavController()
        setUpBottomNavigationBar()
        setUpActionBar()
        setCurrentCountry()
    }

    override fun onSupportNavigateUp() = controller.navigateUp() || super.onSupportNavigateUp()

    private fun initComponent() {
        component = (application as App).appComponent
            .countryActivitySubComponentBuilder()
            .with(this)
            .build()
    }

    private fun initNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        controller = navHostFragment.navController
    }

    private fun setUpActionBar() {
        val appBarConfiguration = AppBarConfiguration(controller.graph)
        findViewById<MaterialToolbar>(R.id.appToolbar)
            .setupWithNavController(controller, appBarConfiguration)
    }

    private fun setUpBottomNavigationBar() {
        val navigation = findViewById<BottomNavigationView>(R.id.bottomNavigationBar)
        NavigationUI.setupWithNavController(navigation, controller)
    }

    private fun inject() {
        component.inject(this)
    }

    private fun setCurrentCountry() {
        val countryName = intent.extras?.getString(resources.getString(R.string.country_key))
        countryName?.let { model.setCurrentCountry(it) }
    }
}
