package by.itacademy.training.travelhelper.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.itacademy.training.travelhelper.databinding.FragmentRouteListBinding

class RouteListFragment : Fragment() {

    private lateinit var binding: FragmentRouteListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRouteListBinding.inflate(inflater, container, false)
        return binding.root
    }
}
