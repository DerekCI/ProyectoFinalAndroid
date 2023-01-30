package dgtic.unam.proyectoandroid

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.app.ActivityCompat
import androidx.core.view.postDelayed
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*

import dgtic.unam.proyectoandroid.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(),
    OnMapReadyCallback,
    GoogleMap.OnMyLocationButtonClickListener,
    GoogleMap.OnMyLocationClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var binding: ActivityMapsBinding
    companion object{
        const val REQUEST_CODE_LOCATION = 0
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val email = bundle?.getString("email")
        //val provider = bundle?.getString("provider")
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        binding.perfilButton.setOnClickListener{
            val profileIntent = Intent(this, ProfileActivity::class.java).apply {
                putExtra("email", email)
                //putExtra("provider", provider.name)
            }
            startActivity(profileIntent)
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


    }

    private fun isLocationPermissionGranted() = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun enableLocation(){
        if(!::mMap.isInitialized) return
        if(isLocationPermissionGranted()){
            mMap.isMyLocationEnabled = true
            //mMap.myLocation
        }else{
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(this, "Acepta los permisos para usar la app", Toast.LENGTH_SHORT).show()
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_LOCATION -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMap.isMyLocationEnabled = true
            } else {
                Toast.makeText(this,
                    "Para activar la localizacion ve a ajustes y acepta los permisos",
                    Toast.LENGTH_SHORT).show()
            }
            else -> {}

        }


    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if(!::mMap.isInitialized) return
        if(!isLocationPermissionGranted()){
            mMap.isMyLocationEnabled = false
            Toast.makeText(this,
                "Para activar la localizacion ve a ajustes y acepta los permisos",
                Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        //createMarker()
        mMap.setOnMyLocationButtonClickListener(this)
        mMap.setOnMyLocationClickListener (this)
        enableLocation()
        val myLocationButton = findViewById<View>(R.id.map).findViewWithTag<View>("GoogleMapMyLocationButton")

        val layoutParams = RelativeLayout.LayoutParams(160, 160)
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE)
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
        layoutParams.setMargins(0,0,16, 16)
        //(myLocationButtonLayout as? RelativeLayout.LayoutParams)?.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0)
        myLocationButton.layoutParams = layoutParams

        var ubicacionActual = fusedLocationClient.lastLocation.addOnSuccessListener {
            var coordenadas = LatLng(it.latitude, it.longitude)
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordenadas, 18f), 4000, null)
        }





        myLocationButton.postDelayed(5000) {
            myLocationButton.callOnClick()
        }


    }
    private fun createMarker(){
        //val favoritePlace = LatLng(28.044195, -16.5363842)
        //mMap.addMarker(MarkerOptions().position(favoritePlace).title("Mi playa favorita!"))
        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(favoritePlace, 18f), 4000, null)
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "Ubicacion actual", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onMyLocationClick(p0: Location) {
        Toast.makeText(this, "Estas en ${p0.latitude}, ${p0.longitude}", Toast.LENGTH_SHORT).show()
    }

}