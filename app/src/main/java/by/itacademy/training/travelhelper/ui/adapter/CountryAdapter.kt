package by.itacademy.training.travelhelper.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.training.travelhelper.R
import by.itacademy.training.travelhelper.databinding.ItemCountryBinding
import by.itacademy.training.travelhelper.di.component.MainActivityScope
import by.itacademy.training.travelhelper.domain.Country
import com.bumptech.glide.Glide
import javax.inject.Inject

@MainActivityScope
class CountryAdapter @Inject constructor(
    private val onItemClickListener: OnCountryItemClickListener
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private val countryList = mutableListOf<Country>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(countryList[position])
        }
        holder.bind(countryList[position])
    }

    override fun getItemCount() = countryList.size

    fun addCountries(countries: List<Country>) {
        with(countryList) {
            clear()
            addAll(countries)
        }
        notifyDataSetChanged()
    }

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCountryBinding.bind(itemView)

        fun bind(country: Country) {
            setCountryImage(country)
            with(binding) {
                countryName.text = country.name
                countryLocation.text = country.region
            }
        }

        private fun setCountryImage(country: Country) {
            Glide.with(itemView.context)
                .load(country.imageUrl)
                .centerCrop()
                .into(binding.countryImageView)
        }
    }
}
