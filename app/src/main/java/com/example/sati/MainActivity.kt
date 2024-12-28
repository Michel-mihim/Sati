package com.example.sati

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marplex.gpslocation.GPSLocation
import com.example.sati.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var gpsLocation: GPSLocation
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gpsLocation = GPSLocation(this)

        gpsLocation.startLocationUpdates()

        gpsLocation.getCurrentLocation {
            location ->
            binding.longitude.text = location.longitude.toString()
            binding.latitude.text = location.latitude.toString()
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