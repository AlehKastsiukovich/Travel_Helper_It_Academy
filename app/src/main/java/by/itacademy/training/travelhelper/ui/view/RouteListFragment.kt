package by.itacademy.training.travelhelper.ui.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.training.travelhelper.R
import by.itacademy.training.travelhelper.databinding.FragmentRoutListBinding
import by.itacademy.training.travelhelper.domain.Route
import by.itacademy.training.travelhelper.ui.adapter.OnRouteClickListener
import by.itacademy.training.travelhelper.ui.adapter.RoutsAdapter
import by.itacademy.training.travelhelper.ui.viewmodel.CountryDescriptionViewModel
import javax.inject.Inject

class RouteListFragment : Fragment(), OnRouteClickListener {

    @Inject lateinit var model: CountryDescriptionViewModel
    @Inject lateinit var routsAdapter: RoutsAdapter

    private lateinit var binding: FragmentRoutListBinding

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
        model.setCurrentRoute(route)
        val mapFragment = MapsFragment()
        activity?.run {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentsContainer, mapFragment)
                .addToBackStack(null)
                .commit()
        }
        setCurrentFragment(mapFragment)
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
            {
                it.data?.let { country ->
                    routsAdapter.addRouts(country.routs)
                }
            }
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as CountryActivity)
            .component
            .routeListSubComponentBuilder()
            .with(this)
            .build()
            .inject(this)
    }

    private fun setCurrentFragment(fragment: MapsFragment) {
        (activity as CountryActivity).setCurrentFragment(fragment)
    }
}
