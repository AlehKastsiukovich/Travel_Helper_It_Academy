package by.itacademy.training.travelhelper.ui.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.itacademy.training.travelhelper.R
import by.itacademy.training.travelhelper.databinding.FragmentMapsBinding
import by.itacademy.training.travelhelper.model.dto.maps.DirectionResponses
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.PolyUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MapsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentMapsBinding
    private lateinit var map: GoogleMap
    private lateinit var fkip: LatLng
    private lateinit var monas: LatLng

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

        fkip = LatLng(-6.3037978, 106.8693713)
        monas = LatLng(-6.1890511, 106.8251573)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        val markerFkip = MarkerOptions()
            .position(fkip)
            .title("FKIP")
        val markerMonas = MarkerOptions()
            .position(monas)
            .title("Monas")

        map.addMarker(markerFkip)
        map.addMarker(markerMonas)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(monas, 11.6f))

        val fromFKIP = fkip.latitude.toString() + "," + fkip.longitude.toString()
        val toMonas = monas.latitude.toString() + "," + monas.longitude.toString()

        lifecycleScope.launch() {
            val result = getResponse()
            drawPolyline(result)
        }
    }

    private suspend fun getResponse() = withContext(Dispatchers.IO) {
        val apiServices = RetrofitClient.apiServices()
        apiServices.getDirection()
    }

    private fun drawPolyline(response: DirectionResponses) {
        val shape = response.routes?.get(0)?.overviewPolyline?.points

        val polyline = PolylineOptions()
            .addAll(PolyUtil.decode(shape))
            .width(8f)
            .color(Color.RED)
        map.addPolyline(polyline)
    }

    private interface ApiServices {
        @GET("/maps/api/directions/json?origin=-6.3037978, 106.8693713&destination=-6.1890511, 106.8251573&key=AIzaSyB9x61iotYLzMuPA810UlnJefQ_-wh8oF8")
        suspend fun getDirection(): DirectionResponses
    }

    private object RetrofitClient {
        fun apiServices(): ApiServices {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://maps.googleapis.com")
                .build()

            return retrofit.create(ApiServices::class.java)
        }
    }
}
