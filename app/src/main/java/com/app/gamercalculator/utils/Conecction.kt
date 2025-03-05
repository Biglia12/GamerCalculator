package com.app.gamercalculator.utils

import android.app.AlertDialog.Builder
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AlertDialog


fun Context.isNetworkAvailable(
    showMessage: Boolean = false,
    title: String = "No hay internet",
    description: String = "Por favor, verifica tu conexión a internet."
): Boolean {
    var isConnected = false
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val nw = connectivityManager.activeNetwork
    val actNw = connectivityManager.getNetworkCapabilities(nw)

    if (actNw != null) {
        isConnected = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    }

    if (!isConnected && showMessage) {

        showErrorDialog(title, description)
    }

    return isConnected
}

fun Context.showErrorDialog(title: String, description: String, dismissAction: () -> Unit = {}) {
    val alertDialogBuilder = Builder(this)
    alertDialogBuilder.setTitle(title)
    // Configura el mensaje.
    alertDialogBuilder
        .setMessage(description)
        .setCancelable(false)
        .setPositiveButton("Si", DialogInterface.OnClickListener { dialog, id ->

        })
        .setNegativeButton("No") { dialog, id -> dialog.cancel() }.create().show()
}
