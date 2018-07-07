package club.bobbychangliu.base

import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater

abstract class BasePresenter<T: BaseView>(val mView: T)

interface BaseView

abstract class BaseActivity<T: BasePresenter<*>> : AppCompatActivity(), BaseView {
	lateinit var mPresenter: T

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		menuInflater.inflate(R.menu.menu_base, menu)
		return true
	}
}