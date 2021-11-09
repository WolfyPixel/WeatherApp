package com.company.restapplication

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.company.restapplication.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class OurApplication : DaggerApplication() {

    var isNetworkEnabled: Boolean = false
        private set

    private val callback = object : ConnectivityManager.NetworkCallback(){

        val availableNetworks = hashSetOf<Network>()

        override fun onAvailable(network: Network) {
            availableNetworks.add(network)
            isNetworkEnabled = availableNetworks.size > 0
        }

        override fun onUnavailable() {
            isNetworkEnabled = false
        }

        override fun onLost(network: Network) {
            availableNetworks.remove(network)
            isNetworkEnabled = availableNetworks.size > 0
        }

    }

    override fun onCreate() {
        super.onCreate()

        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerNetworkCallback(
            NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build(),
            callback
        )
    }

    override fun onTerminate() {
        super.onTerminate()

        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(callback)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

}