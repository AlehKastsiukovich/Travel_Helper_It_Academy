package by.itacademy.training.travelhelper.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.training.travelhelper.R
import by.itacademy.training.travelhelper.databinding.FragmentRoutListBinding
import by.itacademy.training.travelhelper.model.domain.Route
import by.itacademy.training.travelhelper.ui.adapter.OnRouteClickListener
import by.itacademy.training.travelhelper.ui.adapter.RoutsAdapter
import kotlinx.android.synthetic.main.activity_country.view.fragmentsContainer

class RouteListFragment : Fragment(), OnRouteClickListener {

    private lateinit var binding: FragmentRoutListBinding

    private val routsAdapter = RoutsAdapter(this)
    private val mapsFragment = MapsFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoutListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        setUpRecyclerView()
    }

    override fun onRouteClick(route: Route) {
        activity?.run {
            supportFragmentManager
                .beginTransaction()
                .hide(this@RouteListFragment)
                .add(R.id.fragmentsContainer, mapsFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.apply {
            adapter = routsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun setUpAdapter() {
        val country = (activity as CountryActivity).model.currentCountry
        country.observe(
            viewLifecycleOwner,
            Observer {
                it.data?.let { country ->
                    routsAdapter.addRouts(country.routs)
                }
            }
        )
    }
}
