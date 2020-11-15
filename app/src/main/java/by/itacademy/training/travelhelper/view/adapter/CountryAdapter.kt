package by.itacademy.training.travelhelper.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.training.travelhelper.R
import by.itacademy.training.travelhelper.databinding.ItemCountryBinding
import by.itacademy.training.travelhelper.entity.Country
import com.bumptech.glide.Glide

class CountryAdapter : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private val countryList = mutableListOf<Country>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countryList[position])
    }

    override fun getItemCount() = countryList.size

    fun addCountries(countries: List<Country>) {
        countryList.addAll(countries)
        notifyDataSetChanged()
    }

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCountryBinding.bind(itemView)

        fun bind(country: Country) {
            setCountryImage()
            binding.countryName.text = country.name
            binding.countryLocation.text = country.location
        }

        private fun setCountryImage() {
            Glide.with(itemView.context)
                .load(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_background)
                .centerCrop()
                .into(binding.countryImageView)
        }
    }
}
