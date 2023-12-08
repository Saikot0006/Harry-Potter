package com.example.harrypotter.ui

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.example.harrypotter.R
import com.example.harrypotter.broadcastReceiver.ConnectionStatus
import com.example.harrypotter.databinding.ActivityMainBinding
import com.example.harrypotter.databinding.FragmentCharacterListBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() ,ConnectionStatus.ConnectivityReceiverListener{
    private lateinit var binding: ActivityMainBinding
    private var snackbar: Snackbar? = null
    private val connectionStatus = ConnectionStatus()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onPostResume() {
        super.onPostResume()
        registerReceiver(connectionStatus, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        ConnectionStatus.connectivityReceiverListener = this
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(connectionStatus)
    }

    override fun onNetworkConnectionChange(isConnect: Boolean) {
        if(!isConnect){
            snackbar = Snackbar.make(
                binding.mainCL,
                getString(R.string.no_internet_connection),
                Snackbar.LENGTH_INDEFINITE
            ).setAction(
                getString(R.string.turn_on_wifi)
            ) {
                startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
            }
            snackbar?.show()
        }else{
            snackbar?.dismiss()
        }
    }
}