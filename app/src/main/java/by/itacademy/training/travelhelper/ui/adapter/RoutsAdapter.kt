package by.itacademy.training.travelhelper.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.training.travelhelper.R
import by.itacademy.training.travelhelper.databinding.RouteItemBinding
import by.itacademy.training.travelhelper.di.component.RouteListFragmentScope
import by.itacademy.training.travelhelper.domain.Route
import coil.load
import javax.inject.Inject

@RouteListFragmentScope
class RoutsAdapter @Inject constructor(
    private val onRouteClickListener: OnRouteClickListener
) : RecyclerView.Adapter<RoutsAdapter.RouteViewHolder>() {

    private val routeList = mutableListOf<Route>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.route_item, parent, false)
        return RouteViewHolder(view, onRouteClickListener)
    }

    override fun onBindViewHolder(holder: RouteViewHolder, position: Int) {
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

    class RouteViewHolder(
        private val view: View,
        private val onRouteClickListener: OnRouteClickListener
    ) : RecyclerView.ViewHolder(view) {
        private val binding: RouteItemBinding = RouteItemBinding.bind(view)

        fun bind(route: Route) {
            view.setOnClickListener { onRouteClickListener.onRouteClick(route) }
            with(binding) {
                routeImage.load(route.imageUrl) {
                    error(R.drawable.ic_baseline_image_24_placeholder)
                }
                routeTitleText.text = route.title
                routeDescriptionText.text = route.description
            }
        }
    }
}
