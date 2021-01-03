package by.itacademy.training.travelhelper.ui.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import by.itacademy.training.travelhelper.databinding.FragmentCountryDescriptionBinding
import by.itacademy.training.travelhelper.domain.Country
import by.itacademy.training.travelhelper.ui.viewmodel.CountryDescriptionViewModel
import by.itacademy.training.travelhelper.util.Status
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class CountryDescriptionFragment : Fragment() {

    @Inject lateinit var model: CountryDescriptionViewModel

    private lateinit var binding: FragmentCountryDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpFragmentView()
    }

    private fun setUpFragmentView() {
        model.currentCountry.observe(
            viewLifecycleOwner,
            Observer {
                when (it.status) {
                    Status.LOADING -> viewProgressBar()
                    Status.ERROR -> it.message?.let { message -> viewError(message) }
                    Status.SUCCESS -> it.data?.let { country -> viewCountryDescription(country) }
                }
            }
        )
    }

    private fun viewCountryDescription(country: Country) {
        setDescriptionImage(country)
        setDescriptionText(country)
        with(binding) {
            progressBar.visibility = View.INVISIBLE
            mainLayout.visibility = View.VISIBLE
        }
    }

    private fun viewProgressBar() {
        with(binding) {
            mainLayout.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE
        }
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

    private fun setDescriptionText(country: Country) = with(binding) {
        countryText.text = country.name
        regionText.text = country.region
        capitalTextView.text = country.capital
        countryLanguageTextView.text = country.language
        countryDescriptionText.text = country.description
    }

    private fun setDescriptionImage(country: Country) {
        Glide.with(this)
            .load(country.descriptionImageUrl)
            .centerCrop()
            .into(binding.countryDescriptionImage)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as CountryActivity).component.inject(this)
    }
}
