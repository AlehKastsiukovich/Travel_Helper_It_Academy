package by.itacademy.training.travelhelper.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.itacademy.training.travelhelper.R
import by.itacademy.training.travelhelper.databinding.FragmentRouteListBinding
import coil.load
import coil.transform.CircleCropTransformation

class RouteTypeFragment : Fragment() {

    private lateinit var binding: FragmentRouteListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRouteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPictures()
    }

    private fun initPictures() {
        with(binding) {
            transportView.load(R.drawable.transport_pic) {
                transformations(CircleCropTransformation())
            }
            hikingView.load(R.drawable.hiking_trip_pic) {
                transformations(CircleCropTransformation())
            }
        }
    }
}
