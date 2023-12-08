package com.example.harrypotter.broadcastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class ConnectionStatus : BroadcastReceiver() {
    override fun onReceive(context: Context, p1: Intent?) {
        connectivityReceiverListener?.onNetworkConnectionChange(isNetworkAvailable(context))
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ->    true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ->   true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ->   true
                else -> false
            }
        }
        else {
            return connectivityManager.activeNetworkInfo?.isConnected ?: false
        }

    }

    interface ConnectivityReceiverListener{
        fun onNetworkConnectionChange(isConnect : Boolean)
    }
    companion object{
        var connectivityReceiverListener : ConnectivityReceiverListener? = null
    }
}