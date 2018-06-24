package club.bobbychangliu.baselibrary.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import club.bobbychangliu.baselibrary.common.AppManager
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

open class BaseActivity : RxAppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		AppManager.instance.addActivity(this)
	}

	override fun onDestroy() {
		super.onDestroy()
		AppManager.instance.finishActivity(this)
	}
}