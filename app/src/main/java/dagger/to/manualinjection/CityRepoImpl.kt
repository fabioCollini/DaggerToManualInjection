package dagger.to.manualinjection

import android.app.Application
import android.location.Geocoder
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class CityRepoImpl(val app: Application) : CityRepo {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(app)

    private val geocoder = Geocoder(app, Locale.getDefault())

    private suspend fun getLastLocation(): Location =
        suspendCoroutine { continuation ->
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location == null)
                        continuation.resumeWithException(Exception("Location not available"))
                    else
                        continuation.resume(location)
                }
                .addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }

    override suspend fun getCurrentCity(): String =
        withContext(Dispatchers.IO) {
            val location = getLastLocation()
            geocoder.getFromLocation(location.latitude, location.longitude, 10)
                .first { it.locality != null }
                .locality
        }
}