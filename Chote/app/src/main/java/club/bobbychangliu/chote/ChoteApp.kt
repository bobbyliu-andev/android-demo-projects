package club.bobbychangliu.chote

import android.app.Application
import android.content.Context

class ChoteApp : Application() {
	companion object {
		lateinit var ctx: Context
	}

	override fun onCreate() {
		super.onCreate()
		ctx = applicationContext
	}
}