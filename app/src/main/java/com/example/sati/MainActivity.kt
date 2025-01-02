package com.example.sati

import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.marplex.gpslocation.GPSLocation
import com.example.sati.databinding.ActivityMainBinding
import com.marplex.gpslocation.GpsLocationListener
import com.marplex.gpslocation.LocationStatus


class MainActivity : AppCompatActivity() {

    lateinit var gpsLocation: GPSLocation
    private lateinit var binding: ActivityMainBinding
    private var updatesOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gpsLocation = GPSLocation(this)

        Log.d("wtf", updatesOn.toString())

        updatesControll()

        Log.d("wtf", updatesOn.toString())

        binding.getCoordinates.setOnClickListener {
            gpsLocation.getCurrentLocation {
                    location ->

                binding.longitude.text = location.longitude.toString()
                binding.latitude.text = location.latitude.toString()
            }
        }


        gpsLocation.gpsLocationListener = object: GpsLocationListener {
            override fun onLocationReceived(locations: List<Location>) {
                TODO("Not yet implemented")
            }

            override fun onLocationStatusReceived(status: LocationStatus) {
                when (status) {
                    LocationStatus.MISSING_PERMISSIONS -> TODO("Ask gps permissions")
                    LocationStatus.PERMISSIONS_DENIED -> TODO("Ask gps permissions")
                    LocationStatus.NO_GPS -> gpsLocation.showLocationSettings(this@MainActivity)
                }
            }

            override fun onLocationException(exception: Exception) {
                TODO("Not yet implemented")
            }
        }


    }

    fun updatesControll() {
        if (updatesOn == false) {
            updatesOn = true
            gpsLocation.startLocationUpdates()
        } else {
            updatesOn = false
            gpsLocation.stopLocationUpdates()
        }
    }

    override fun onPause() {
        super.onPause()

            gpsLocation.stopLocationUpdates()
    }

    override fun onDestroy() {
        super.onDestroy()

            gpsLocation.stopLocationUpdates()
    }
}