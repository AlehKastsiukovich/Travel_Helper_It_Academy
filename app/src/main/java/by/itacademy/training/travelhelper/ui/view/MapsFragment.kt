package by.itacademy.training.travelhelper.ui.view

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import by.itacademy.training.travelhelper.R
import by.itacademy.training.travelhelper.domain.Route
import by.itacademy.training.travelhelper.model.dto.maps.DirectionResponse
import by.itacademy.training.travelhelper.ui.adapter.MarkerInfoWindowAdapter
import by.itacademy.training.travelhelper.ui.viewmodel.CountryDescriptionViewModel
import by.itacademy.training.travelhelper.util.MapPositionHelper
import by.itacademy.training.travelhelper.util.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
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
    @Inject lateinit var mapPositionHelper: MapPositionHelper

    private lateinit var route: Route
    private lateinit var map: GoogleMap
    private lateinit var locationClient: FusedLocationProviderClient

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
        initLocationClient()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.map = googleMap
        requestLocation()
        addMarkersToMap()
        model.direction.observe(
            viewLifecycleOwner,
            { event ->
                when (event.status) {
                    Status.SUCCESS -> event.data?.let { drawPolyline(it) }
                    Status.ERROR -> event.message?.let { showErrorMessage(it) }
                }
            }
        )

        map.setInfoWindowAdapter(
            MarkerInfoWindowAdapter(
                (activity as Activity),
                this.mapPositionHelper
            )
        )
    }

    private fun addMarkersToMap() {
        if (route.markers.isNotEmpty()) {
            route.markers.forEach { marker ->
                val markerOptions = MarkerOptions().apply {
                    title(marker.title)
                    snippet(marker.description)
                    position(LatLng(marker.latitude, marker.longitude))
                }
                map.addMarker(markerOptions)
            }
            setCameraView()
        }
    }

    private fun addCurrentPositionMarker(location: Location) {
        val marker = MarkerOptions().apply {
            title(POSITION_MARKER_TITLE)
            position(LatLng(location.latitude, location.longitude))
            icon(
                mapPositionHelper.bitmapDescriptorFromVector(
                    activity as CountryActivity, R.drawable.position
                )
            )
        }
        map.addMarker(marker)
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
            color(Color.BLUE)
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

    private fun initLocationClient() {
        locationClient = LocationServices.getFusedLocationProviderClient(activity as CountryActivity)
    }

    private fun requestLocation() {
        if (ActivityCompat.checkSelfPermission(
                context as CountryActivity, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                    context as CountryActivity, Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context as CountryActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MapsFragment.LOCATION_PERMISSION_CODE
            )
            return
        }
        val locationRequest = mapPositionHelper.createLocationRequest()
        val callback = locationCallback()

        with(locationClient) {
            requestLocationUpdates(locationRequest, callback, null)
            lastLocation.addOnSuccessListener {
                addCurrentPositionMarker(it)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as CountryActivity).component.inject(this)
    }

    private fun locationCallback(): LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            if (locationResult == null) {
                return
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            LOCATION_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    requestLocation()
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_CODE = 9999
        private const val POSITION_MARKER_TITLE = "Your position"
        private const val DEFAULT_MAP_ZOOM = 7f
        private const val POLYLINE_WIDTH = 9f
    }
}
