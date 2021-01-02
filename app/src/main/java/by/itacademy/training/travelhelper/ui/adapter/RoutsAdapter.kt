package by.itacademy.training.travelhelper.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.training.travelhelper.model.domain.Route

class RoutsAdapter : RecyclerView.Adapter<RoutsAdapter.RoutViewHolder>() {

    private val routeList = mutableListOf<Route>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutViewHolder {
        return null
    }

    override fun onBindViewHolder(holder: RoutViewHolder, position: Int) {
        holder.bind(routeList[position])
    }

    override fun getItemCount() = routeList.size

    fun addRouts(routs: List<Route>) {
        with(routeList) {
            clear()
            addAll(routs)
        }
        notifyDataSetChanged()
    }

    class RoutViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(route: Route) {
        }
    }
}
