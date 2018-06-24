package club.bobbychangliu.baselibrary.ext

import android.content.Context
import android.net.ConnectivityManager
import android.widget.EditText
import club.bobbychangliu.baselibrary.rx.BaseSubscriber
import com.trello.rxlifecycle2.LifecycleProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

// *** 01
fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>, lifecycleProvider: LifecycleProvider<*>) {

    this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribe(subscriber)
}

// check network
fun Context.isNetWorkAvailable(): Boolean {
	val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
	val networkInfo = cm.activeNetworkInfo
	return networkInfo != null && networkInfo.isConnected
}

fun Context.isWifiAvailable(): Boolean {
	val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
	val networkInfo = cm.activeNetworkInfo
	return networkInfo != null && networkInfo.type == ConnectivityManager.TYPE_WIFI
}

fun Context.is3gConnected(): Boolean {
	val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
	val networkInfo = cm.activeNetworkInfo
	return networkInfo != null && networkInfo.type == ConnectivityManager.TYPE_MOBILE
}

















fun EditText.getString(): String = text.toString()