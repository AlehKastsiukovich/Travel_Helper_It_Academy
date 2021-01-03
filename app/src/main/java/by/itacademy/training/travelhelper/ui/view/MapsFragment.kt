package by.itacademy.training.travelhelper.ui.view

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import by.itacademy.training.travelhelper.R
import by.itacademy.training.travelhelper.domain.Route
import by.itacademy.training.travelhelper.model.dto.maps.DirectionResponse
import by.itacademy.training.travelhelper.ui.viewmodel.CountryDescriptionViewModel
import by.itacademy.training.travelhelper.util.Status
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.material.snackbar.Snackbar
import com.google.maps.android.PolyUtil
import javax.inject.Inject

class MapsFragment : Fragment(), OnMapReadyCallback {

    @Inject lateinit var model: CountryDescriptionViewModel

    private lateinit var route: Route
    private lateinit var map: GoogleMap

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

        initCurrentRoute()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        addMarkersToMap()
        model.direction.observe(
            viewLifecycleOwner, { event ->
                when(event.status) {
                    Status.SUCCESS -> event.data?.let { drawPolyline(it) }
                    Status.ERROR -> event.message?.let { showErrorMessage(it) }
                }
            }
        )
    }

    private fun addMarkersToMap() {
        if (route.markers.isNotEmpty()) {
            route.markers.forEach { marker ->
                val markerOptions = MarkerOptions().apply {
                    title(marker.title)
                    position(LatLng(marker.latitude, marker.longitude))
                }
                map.addMarker(markerOptions)
            }
            setCameraView()
        }
    }

    private fun setCameraView() {
        map.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(route.markers[0].latitude, route.markers[0].longitude), DEFAULT_MAP_ZOOM
            )
        )
    }

    private fun initCurrentRoute() {
        model.route.observe(
            viewLifecycleOwner, { route = it }
        )
    }

    private fun drawPolyline(response: DirectionResponse) {
        val shape = response.routes?.get(0)?.overviewPolyline?.points
        val polyline = PolylineOptions().apply {
            addAll(PolyUtil.decode(shape))
            width(POLYLINE_WIDTH)
            color(Color.RED)
        }
        map.addPolyline(polyline)
    }

    private fun showErrorMessage(message: String) {
        Snackbar.make(
            this.requireView(),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as CountryActivity).component.inject(this)
    }

    companion object {
        private const val DEFAULT_MAP_ZOOM = 6f
        private const val POLYLINE_WIDTH = 8f
    }
}
