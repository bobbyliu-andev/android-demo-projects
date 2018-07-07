package club.bobbychangliu.base

import android.app.Application
import android.content.Context

class BaseApplication : Application() {
	companion object {
		lateinit var ctx: Context
	}

	override fun onCreate() {
		super.onCreate()
		ctx = applicationContext
	}
}