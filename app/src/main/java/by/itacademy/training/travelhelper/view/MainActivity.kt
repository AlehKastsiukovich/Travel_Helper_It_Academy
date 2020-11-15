package by.itacademy.training.travelhelper.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.training.travelhelper.R
import by.itacademy.training.travelhelper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentsContainer, CountryListFragment())
            .addToBackStack(null)
            .commit()
    }
}
