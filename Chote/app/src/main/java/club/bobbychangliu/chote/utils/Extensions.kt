package club.bobbychangliu.chote.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import club.bobbychangliu.chote.ChoteApp

fun Activity.hasInternet(): Boolean {
	val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
	val activeNetwork = cm.activeNetworkInfo
	val isConnected = activeNetwork?.isConnectedOrConnecting
	logi("Connection is ${isConnected ?: "Error Null"}")
	return isConnected ?: false
}

fun toast(msg: String) {
	Toast.makeText(ChoteApp.ctx, msg, Toast.LENGTH_SHORT).show()
}

fun logi(msg: String) {
	Log.i("DEBUG LOG", msg)
}