package by.itacademy.training.travelhelper.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.itacademy.training.travelhelper.R
import by.itacademy.training.travelhelper.databinding.FragmentMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions

class MapsFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnPolylineClickListener {

    private lateinit var binding: FragmentMapsBinding

//    private val callback = OnMapReadyCallback { googleMap ->
//        val Minsk = LatLng(53.89, 27.56)
//        googleMap.addMarker(MarkerOptions().position(Minsk).title("Marker in Minsk"))
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Minsk))
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val polyline = googleMap?.run {
            addPolyline(
                PolylineOptions()
                    .clickable(true)
                    .add(
                        LatLng(-35.016, 143.321),
                        LatLng(-34.747, 145.592),
                        LatLng(-34.364, 147.891),
                        LatLng(-33.501, 150.217),
                        LatLng(-32.306, 149.248),
                        LatLng(-32.491, 147.309)
                    )
            )
        }

        googleMap?.setOnPolylineClickListener(this)
    }

    override fun onPolylineClick(p0: Polyline?) {
        TODO("Not yet implemented")
    }
}
