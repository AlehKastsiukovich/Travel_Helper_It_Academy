package by.itacademy.training.travelhelper.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CountryAdapter : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private val countryList = mutableListOf<Country>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
