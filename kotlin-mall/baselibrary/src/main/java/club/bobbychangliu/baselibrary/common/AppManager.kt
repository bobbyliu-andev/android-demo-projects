package club.bobbychangliu.baselibrary.common

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

class AppManager {

	val activityStack = Stack<Activity>()

	companion object {
		val instance: AppManager by lazy { AppManager() }
	}

	// activity in, out, clear, get
	fun addActivity(activity: Activity) {
		activityStack.add(activity)
	}

	fun finishActivity(activity: Activity) {
		activity.finish()
		activityStack.remove(activity)
	}

	fun finishAllActivity() {
		activityStack.forEach { finishActivity(it) }
	}

	fun currentActivity(): Activity {
		return activityStack.lastElement()
	}

	fun exitApp(context: Context) {
		finishAllActivity()
		// exit app
		val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
		activityManager.killBackgroundProcesses(context.packageName)
	}
}