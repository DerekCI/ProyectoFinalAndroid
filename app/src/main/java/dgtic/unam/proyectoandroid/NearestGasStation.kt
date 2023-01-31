package dgtic.unam.proyectoandroid

import android.location.Location.*
import android.util.Log
import com.google.android.gms.maps.model.LatLng

class NearestGasStation {

    companion object {

        fun getTopFiveNearestStations(stations: List<GasStation>, actualLocation: LatLng): List<GasStation> {

            val distancias = stations.map{
                GasolineraDistancia(calcularDistancia(actualLocation, it.location), it)
            }

            val ordenadas = distancias.sorted()
            val lasPrimeras5 = ordenadas.take(5)
            Log.d("5primeras", "${lasPrimeras5.size}")
            Log.d("5primeras", "${lasPrimeras5[0]}")
            Log.d("5primeras", "${lasPrimeras5[1]}")
            Log.d("5primeras", "${lasPrimeras5[2]}")
            Log.d("5primeras", "${lasPrimeras5[3]}")



            val soloLasGasolineras = lasPrimeras5.map {
                it.gasolinera
            }


            return soloLasGasolineras
        }

        fun calcularDistancia(origen: LatLng, destino: LatLng): Float{
            val results = FloatArray(1)
            distanceBetween(origen.latitude, origen.longitude, destino.latitude, destino.longitude, results)
            return results.min()
        }
    }
}

data class GasStation(
    val location: LatLng
)

data class GasolineraDistancia(
    val distancia: Float,
    val gasolinera: GasStation,
): Comparable<GasolineraDistancia> {
    override fun compareTo(other: GasolineraDistancia): Int {
        return when {
            this.distancia > other.distancia -> 1
            other.distancia > this.distancia -> -1
            else -> 0
        }
    }
}

fun getGasStation(): List<GasStation>{
    val gasStations: MutableList<GasStation> = ArrayList()
    gasStations.add(GasStation(LatLng(18.3969941, -99.0992573)))
    gasStations.add(GasStation(LatLng(19.40909602,	-99.16767659)))
    gasStations.add(GasStation(LatLng(19.45998011,	-99.18692055)))
    gasStations.add(GasStation(LatLng(19.37928112,	-99.1881301
    )))
    gasStations.add(GasStation(LatLng(19.45229044,	-99.14591838)))
    gasStations.add(GasStation(LatLng(19.41289732,	-99.16110616)))
    gasStations.add(GasStation(LatLng(19.45617586,	-99.12876571)))
    gasStations.add(GasStation(LatLng(19.40147175,	-99.17567329)))
    gasStations.add(GasStation(LatLng(19.39231397,	-99.16577711)))
    gasStations.add(GasStation(LatLng(19.42003549,	-99.17647583)))
    gasStations.add(GasStation(LatLng(19.42270362,	-99.16108749)))
    gasStations.add(GasStation(LatLng(19.41572357,	-99.15459188)))
    gasStations.add(GasStation(LatLng(19.48513199,	-99.11893961)))
    gasStations.add(GasStation(LatLng(19.34754787,	-99.1876642
    )))
    gasStations.add(GasStation(LatLng(9.42222093,	-99.20733727)))
    gasStations.add(GasStation(LatLng(19.38846714,	-99.1327613
    )))
    gasStations.add(GasStation(LatLng(9.39747485,	-99.16727402)))
    gasStations.add(GasStation(LatLng(19.4153454	,-99.16564411
    )))
    gasStations.add(GasStation(LatLng(9.40334813,	-99.18325998)))
    gasStations.add(GasStation(LatLng(19.40097757,	-99.1702492
    )))
    gasStations.add(GasStation(LatLng(19.47479	,-99.12130259
    )))
    gasStations.add(GasStation(LatLng(19.43172578,	-99.15952346)))
    gasStations.add(GasStation(LatLng(19.42762623,	-99.11993405)))
    gasStations.add(GasStation(LatLng(19.3995131	,-99.17034631
    )))
    gasStations.add(GasStation(LatLng(19.45435927,	-99.18772576)))
    gasStations.add(GasStation(LatLng(19.45800632,	-99.20019562)))
    gasStations.add(GasStation(LatLng(19.38846747,	-99.13857586)))
    gasStations.add(GasStation(LatLng(19.4161668	,-99.18233724
    )))
    gasStations.add(GasStation(LatLng(19.49493369,	-99.13252805)))
    gasStations.add(GasStation(LatLng(19.44553434,	-99.20177218)))
    gasStations.add(GasStation(LatLng(19.42845249,	-99.15526512)))
    gasStations.add(GasStation(LatLng(19.43857776,	-99.16793218)))
    gasStations.add(GasStation(LatLng(19.39008535,	-99.20519237)))
    gasStations.add(GasStation(LatLng(19.40169303,	-99.12519614)))
    gasStations.add(GasStation(LatLng(19.39423825,	-99.13851627)))
    gasStations.add(GasStation(LatLng(19.48780405,	-99.151148
    )))
    gasStations.add(GasStation(LatLng(19.42749369,	-99.13920406)))
    gasStations.add(GasStation(LatLng(19.42378793,	-99.15291003)))
    gasStations.add(GasStation(LatLng(19.39746642,	-99.15077554)))
    gasStations.add(GasStation(LatLng(19.30435114,	-99.06007263)))
    gasStations.add(GasStation(LatLng(19.46705459,	-99.12434662)))
    gasStations.add(GasStation(LatLng(19.35724067,	-98.99404275)))
    gasStations.add(GasStation(LatLng(19.44649298,	-99.13881776)))
    gasStations.add(GasStation(LatLng(19.3602713	,-99.17155062
    )))
    gasStations.add(GasStation(LatLng(1.31377768,	-99.12428277)))
    gasStations.add(GasStation(LatLng(19.3727963	,-99.09931964
    )))
    gasStations.add(GasStation(LatLng(19.36761145,	-99.14226622)))
    gasStations.add(GasStation(LatLng(19.34631758,	-99.0195258
    )))
    gasStations.add(GasStation(LatLng(19.43833251,	-99.11910947)))
    gasStations.add(GasStation(LatLng(19.41563626,	-99.17007402)))
    gasStations.add(GasStation(LatLng(19.45135138,	-99.16185902)))
    gasStations.add(GasStation(LatLng(19.40207856,	-99.14473189)))
    gasStations.add(GasStation(LatLng(19.34315757,	-99.03935062)))
    gasStations.add(GasStation(LatLng(19.5607276	,-99.13403002
    )))
    gasStations.add(GasStation(LatLng(19.33894	,-99.12748
    )))
    gasStations.add(GasStation(LatLng(19.3838602	,-99.16396139)))
    gasStations.add(GasStation(LatLng(19.26781949,	-99.11618013)))
    gasStations.add(GasStation(LatLng(19.44134224,	-99.20975331)))
    gasStations.add(GasStation(LatLng(19.40162472,	-99.1365534
    )))
    gasStations.add(GasStation(LatLng(19.3607472	,-99.18901809
    )))
    gasStations.add(GasStation(LatLng(19.42710106,	-99.13927874)))
    gasStations.add(GasStation(LatLng(19.37228541,	-99.17283809)))
    gasStations.add(GasStation(LatLng(19.44978592,	-99.16405646)))
    gasStations.add(GasStation(LatLng(19.35065125,	-99.16389843)))
    gasStations.add(GasStation(LatLng(19.34564743,	-99.02015237)))
    gasStations.add(GasStation(LatLng(19.41016009,	-99.08042543)))
    gasStations.add(GasStation(LatLng(19.43611784,	-99.18194897)))
    gasStations.add(GasStation(LatLng(19.43730916,	-99.16512432)))
    gasStations.add(GasStation(LatLng(19.43595964,	-99.1817747
    )))
    gasStations.add(GasStation(LatLng(19.45814765,	-99.11798947)))
    gasStations.add(GasStation(LatLng(19.45812129,	-99.1179078
    )))
    gasStations.add(GasStation(LatLng(19.46677653,	-99.14206349)))
    gasStations.add(GasStation(LatLng(19.43609474,	-99.18192352)))
    gasStations.add(GasStation(LatLng(19.37234654,	-99.09935529)))
    gasStations.add(GasStation(LatLng(19.43595964,	-99.1817747
    )))
    gasStations.add(GasStation(LatLng(19.42394398,	-99.16691452)))
    gasStations.add(GasStation(LatLng(19.44093329,	-99.21013573)))
    gasStations.add(GasStation(LatLng(19.45279716,	-99.21851006)))
    gasStations.add(GasStation(LatLng(19.43675397,	-99.21015502)))
    gasStations.add(GasStation(LatLng(19.43756733,	-99.17678472)))
    gasStations.add(GasStation(LatLng(19.42630151,	-99.17057313)))
    gasStations.add(GasStation(LatLng(19.37259086,	-99.09941398)))
    gasStations.add(GasStation(LatLng(19.39727321,	-99.05829117)))
    gasStations.add(GasStation(LatLng(19.34767345,	-99.1904719
    )))
    gasStations.add(GasStation(LatLng(19.52265381,	-99.14220597)))
    gasStations.add(GasStation(LatLng(19.52102075,	-99.14214408)))
    gasStations.add(GasStation(LatLng(19.43647738,	-99.19966747)))
    gasStations.add(GasStation(LatLng(19.39857351,	-99.07787346)))
    gasStations.add(GasStation(LatLng(19.4069206	,-99.20091791
    )))
    gasStations.add(GasStation(LatLng(19.42848989,	-99.09172351)))
    gasStations.add(GasStation(LatLng(19.39240015,	-99.04942426)))
    gasStations.add(GasStation(LatLng(19.4632448	,-99.05682851
    )))
    gasStations.add(GasStation(LatLng(19.53120381,	-99.14190485)))
    gasStations.add(GasStation(LatLng(19.46218435,	-99.17785188)))
    gasStations.add(GasStation(LatLng(19.44486021,	-99.13504696)))
    gasStations.add(GasStation(LatLng(19.29288277,	-99.21658526)))
    gasStations.add(GasStation(LatLng(19.37324848,	-99.04016997)))
    gasStations.add(GasStation(LatLng(19.34663511,	-99.20234096)))
    gasStations.add(GasStation(LatLng(19.4565807	,-99.13783016
    )))
    gasStations.add(GasStation(LatLng(19.29774743,	-99.15173376)))
    gasStations.add(GasStation(LatLng(19.33710366,	-99.18444418)))
    gasStations.add(GasStation(LatLng(19.30169072,	-99.16741692)))
    gasStations.add(GasStation(LatLng(19.36971472,	-99.18007953)))
    gasStations.add(GasStation(LatLng(19.47494212,	-99.1394881)))
    gasStations.add(GasStation(LatLng(19.40704372,	-99.13612739)))
    gasStations.add(GasStation(LatLng(19.50223284,	-99.1493369)))
    gasStations.add(GasStation(LatLng(19.49097457,	-99.17333015)))
    gasStations.add(GasStation(LatLng(19.41794694,	-99.17466649)))
    gasStations.add(GasStation(LatLng(19.48201746,	-99.18753005)))
    gasStations.add(GasStation(LatLng(19.47126824,	-99.16597835)))
    gasStations.add(GasStation(LatLng(19.36981042,	-99.13732211)))
    gasStations.add(GasStation(LatLng(19.45778825,	-99.15123312)))
    gasStations.add(GasStation(LatLng(19.35274111,	-99.0145848)))
    gasStations.add(GasStation(LatLng(19.36220597,	-99.15719496)))
    gasStations.add(GasStation(LatLng(19.31595769,	-99.1249071)))
    gasStations.add(GasStation(LatLng(19.39434637,	-99.14134037)))
    gasStations.add(GasStation(LatLng(19.30848271,	-99.13352682)))
    gasStations.add(GasStation(LatLng(19.45416495,	-99.21681985)))
    gasStations.add(GasStation(LatLng(19.33350197,	-99.20113079)))
    gasStations.add(GasStation(LatLng(19.38044642,	-99.25186886)))
    gasStations.add(GasStation(LatLng(19.30541073,	-99.06314589)))
    gasStations.add(GasStation(LatLng(19.36058531,	-99.03525288)))
    gasStations.add(GasStation(LatLng(19.39497671,	-99.18092459)))
    gasStations.add(GasStation(LatLng(19.28317528,	-99.21713621)))
    gasStations.add(GasStation(LatLng(19.37866936,	-99.07707102)))
    gasStations.add(GasStation(LatLng(19.3876358	,-99.26287544)))
    gasStations.add(GasStation(LatLng(19.22547577,	-99.2116633)))
    gasStations.add(GasStation(LatLng(19.37798236,	-99.08176304)))
    gasStations.add(GasStation(LatLng(19.25719692,	-99.02356714)))
    gasStations.add(GasStation(LatLng(19.39647829,	-99.16194863)))
    gasStations.add(GasStation(LatLng(19.42086205,	-99.1271033)))
    gasStations.add(GasStation(LatLng(19.28248239,	-99.14210903)))
    gasStations.add(GasStation(LatLng(19.39226995,	-99.20210672)))
    gasStations.add(GasStation(LatLng(19.2291238	,-99.19699989
    )))
    gasStations.add(GasStation(LatLng(19.41205631,	-99.15971371)))
    gasStations.add(GasStation(LatLng(19.4432716	,-99.18317997
    )))
    gasStations.add(GasStation(LatLng(19.35663223,	-99.09787205)))
    gasStations.add(GasStation(LatLng(19.37983826,	-99.08129668)))
    gasStations.add(GasStation(LatLng(19.38550381,	-99.22616726)))
    gasStations.add(GasStation(LatLng(19.25721995,	-99.01829804)))
    gasStations.add(GasStation(LatLng(19.35111469,	-99.21848313)))
    gasStations.add(GasStation(LatLng(19.423135	,-99.12949737)))
    gasStations.add(GasStation(LatLng(19.44226012,	-99.08601109)))
    gasStations.add(GasStation(LatLng(19.4425418	,-99.08597015)))
    gasStations.add(GasStation(LatLng(19.38355977,	-99.14530462)))
    gasStations.add(GasStation(LatLng(19.33681674,	-99.14246725)))
    gasStations.add(GasStation(LatLng(19.30283791,	-99.20701394)))
    gasStations.add(GasStation(LatLng(19.3277207	,-99.21927721)))
    gasStations.add(GasStation(LatLng(19.48154471,	-99.16508627)))
    gasStations.add(GasStation(LatLng(19.37345007,	-99.01793282)))
    gasStations.add(GasStation(LatLng(19.4511672	,-99.11569976)))
    gasStations.add(GasStation(LatLng(19.31686552,	-99.12741385)))
    gasStations.add(GasStation(LatLng(19.4552694	,-99.16100498)))
    gasStations.add(GasStation(LatLng(19.40130877,	-99.16226381)))
    gasStations.add(GasStation(LatLng(19.49615781,	-99.15911236)))
    gasStations.add(GasStation(LatLng(19.38127593,	-99.11109193)))
    gasStations.add(GasStation(LatLng(19.36582638,	-99.09152234)))
    gasStations.add(GasStation(LatLng(19.3371588	,-99.06454995)))
    gasStations.add(GasStation(LatLng(19.33830664,	-99.19282561)))
    gasStations.add(GasStation(LatLng(19.28136541,	-99.21491347)))
    gasStations.add(GasStation(LatLng(19.37614491,	-99.06219418)))
    gasStations.add(GasStation(LatLng(19.4098708	,-99.07286874)))
    gasStations.add(GasStation(LatLng(19.38990397,	-99.20350603)))
    gasStations.add(GasStation(LatLng(19.39010596,	-99.20310057)))
    gasStations.add(GasStation(LatLng(19.51327834,	-99.14693838)))
    gasStations.add(GasStation(LatLng(19.51142806,	-99.14903083)))
    gasStations.add(GasStation(LatLng(19.49025315,	-99.0914286)))
    gasStations.add(GasStation(LatLng(19.36644254,	-99.23259481)))
    gasStations.add(GasStation(LatLng(19.42055	,-99.13419895)))
    gasStations.add(GasStation(LatLng(19.44058705,	-99.0856939)))
    gasStations.add(GasStation(LatLng(19.30559396,	-99.20394364)))
    gasStations.add(GasStation(LatLng(19.50560509,	-99.19050737)))
    gasStations.add(GasStation(LatLng(19.39706753,	-99.07285479)))
    gasStations.add(GasStation(LatLng(19.49028129,	-99.1445814)))
    gasStations.add(GasStation(LatLng(19.43574198,	-99.1819921)))
    gasStations.add(GasStation(LatLng(19.43252149,	-99.21310157)))
    gasStations.add(GasStation(LatLng(19.4613431	,-99.14068356)))
    gasStations.add(GasStation(LatLng(19.43610725,	-99.1819373)))
    gasStations.add(GasStation(LatLng(19.48800875,	-99.16265152)))
    gasStations.add(GasStation(LatLng(19.38345	,-99.09287627)))
    gasStations.add(GasStation(LatLng(19.36397688,	-99.00538649)))
    gasStations.add(GasStation(LatLng(19.39819175,	-99.14553144)))
    gasStations.add(GasStation(LatLng(19.26751362,	-99.16467625)))
    gasStations.add(GasStation(LatLng(19.47467577,	-99.10002428)))
    gasStations.add(GasStation(LatLng(19.4824064	,-99.19830059)))
    gasStations.add(GasStation(LatLng(19.52766838,	-99.15743541)))
    gasStations.add(GasStation(LatLng(19.37941406,	-99.02414156)))
    gasStations.add(GasStation(LatLng(19.4536284	,-99.15004874)))
    gasStations.add(GasStation(LatLng(19.41013352,	-99.08037936)))
    gasStations.add(GasStation(LatLng(19.36677389,	-99.15585395)))
    gasStations.add(GasStation(LatLng(19.46540926,	-99.12647756)))
    gasStations.add(GasStation(LatLng(19.25727149,	-99.02134629)))
    gasStations.add(GasStation(LatLng(19.37533851,	-99.07842596)))
    gasStations.add(GasStation(LatLng(19.41745177,	-99.12731037)))
    gasStations.add(GasStation(LatLng(19.49124307,	-99.13608611)))
    gasStations.add(GasStation(LatLng(19.40754898,	-99.08306813)))
    gasStations.add(GasStation(LatLng(19.37223389,	-99.01448408)))
    gasStations.add(GasStation(LatLng(19.3764058	,-99.08677734)))
    gasStations.add(GasStation(LatLng(19.30863663,	-99.13294699)))

    return gasStations
}
