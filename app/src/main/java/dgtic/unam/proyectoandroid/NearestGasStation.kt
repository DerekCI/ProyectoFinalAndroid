package dgtic.unam.proyectoandroid

import android.location.Location
import com.google.android.gms.maps.model.LatLng

class NearestGasStation {

    companion object {

        fun getTopFiveNearestStations(stations: List<GasStation>, actualLocation: LatLng): List<GasStation> {


            //iterar en stations

            val distancias = stations.map{
                GasolineraDistancia(calcularDistancia(actualLocation, it.location), it)
            }
            val ordenadas = distancias.sorted()
            val lasPrimeras5 = ordenadas.take(5)


            val soloLasGasolineras = lasPrimeras5.map { it.gasolinera }


            return soloLasGasolineras
        }

        fun calcularDistancia(origen: LatLng, destino: LatLng): Float{
            var resultados = floatArrayOf()
            Location.distanceBetween(origen.latitude, origen.longitude, destino.latitude, destino.longitude, resultados)
            return resultados.min()
        }
    }
}

data class GasStation(
    val name: String,
    val location: LatLng,
    val price: Double,
)

data class GasolineraDistancia(
    val distancia: Float,
    val gasolinera: GasStation
): Comparable<GasolineraDistancia> {
    override fun compareTo(other: GasolineraDistancia): Int {
        return when {
            this.distancia > other.distancia -> 1
            other.distancia > this.distancia -> -1
            else -> 0
        }
    }
}
