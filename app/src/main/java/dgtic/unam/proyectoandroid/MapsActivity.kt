package dgtic.unam.proyectoandroid

import android.Manifest
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.content.IntentSender
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.google.android.gms.common.api.ResolvableApiException
import android.location.Location
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.location.LocationServices.getFusedLocationProviderClient
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.gms.tasks.Task
import dgtic.unam.proyectoandroid.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private val SOLICITAR_ACCES_FINE_LOCATION = 1000
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: MyLocationCallback


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        iniciarGeolocalizacion()

    }

    private fun iniciarGeolocalizacion() {
        fusedLocationProviderClient = getFusedLocationProviderClient(this)
        locationCallback = MyLocationCallback()
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 5000
        locationRequest.fastestInterval = 5000
    }


    //verificamos los permisos de ubicacion del usuario
    private fun verificarPermisos(cancel: ()-> Unit, ok: ()-> Unit){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                //se nego los permisos
                cancel()
            }else{
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), SOLICITAR_ACCES_FINE_LOCATION)
            }
        }else{
            //encender gps del usuario
            val request = LocationRequest.create().apply {
                interval = 2000
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            }
            val builder= LocationSettingsRequest.Builder().addLocationRequest(request)
            val client: SettingsClient = LocationServices.getSettingsClient(this)
            val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())
            task.addOnFailureListener{
                if (it is ResolvableApiException){
                    try{
                        it.startResolutionForResult(this, 12345)
                    }catch (sendEx: IntentSender.SendIntentException){
                    }
                }
            }.addOnSuccessListener {
                //el gps se enciende
            }
            //se activo el gps
            ok()
        }
    }
    private fun mostrarDialogoPermiso(){
        ActivityCompat.requestPermissions(this@MapsActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), SOLICITAR_ACCES_FINE_LOCATION)
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
    }

    override fun onResume() {
        super.onResume()
        verificarPermisos(cancel = {
            //moestramos el dialogo para solicitarle al usuario que encienda su gps
            mostrarDialogoPermiso()
        }, ok = {
            //iniciamos la geolocalizacion del usuario con un listener o oyente
            agregarOyenteUbicacion()
        })
    }

    override fun onPause() {
        super.onPause()
        removeLocationListener()
    }

    private fun removeLocationListener(){
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    @SuppressLint("MissingPermission")
    private fun agregarOyenteUbicacion(){
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    //Callback para mostrar un marcador en el mapa de google
    inner class MyLocationCallback: LocationCallback(){
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)

            val location = locationResult.lastLocation

            location?.run {
                val latLng = LatLng(latitude, longitude)

                mMap.addMarker(MarkerOptions().position(latLng).title("Mi ubicacion"))
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
                Log.e("Ubicacion", "Latitud: $latitude, Longitud: $longitude")
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            SOLICITAR_ACCES_FINE_LOCATION->{
                if((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    //ESTE ETODO HACE USO DE FUSED LOCATION PROVIDER
                    agregarOyenteUbicacion()
                }else{
                    //si el usuario denego el permiso le mostramos el mensaje "permiso denegado"
                    val toast = Toast.makeText(applicationContext, "Permiso denegado", Toast.LENGTH_SHORT)
                    toast.show()
                }
                return
            }
        }
    }


}