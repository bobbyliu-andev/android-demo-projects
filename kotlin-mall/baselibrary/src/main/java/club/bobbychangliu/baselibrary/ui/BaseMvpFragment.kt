package club.bobbychangliu.baselibrary.ui

import android.os.Bundle
import club.bobbychangliu.baselibrary.common.BaseApplication
import club.bobbychangliu.baselibrary.injection.component.ActivityComponent
import club.bobbychangliu.baselibrary.injection.component.DaggerActivityComponent
import club.bobbychangliu.baselibrary.injection.module.ActivityModule
import club.bobbychangliu.baselibrary.injection.module.LifecycleProviderModule
import club.bobbychangliu.baselibrary.presenter.BasePresenter
import club.bobbychangliu.baselibrary.presenter.BaseView
import org.jetbrains.anko.act
import javax.inject.Inject

abstract class BaseMvpFragment<T: BasePresenter<*>> : BaseFragment(), BaseView {

	@Inject
	lateinit var mPresenter: T

	lateinit var activityComponent: ActivityComponent

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		initActivityInjection()
		injectComponent()
	}

	private fun initActivityInjection() {

		activity?.act?.let { act ->
			activityComponent = DaggerActivityComponent.builder()
					.appComponent((act.application as BaseApplication).appComponent)
					.activityModule(ActivityModule(act))
					.lifecycleProviderModule(LifecycleProviderModule(this))
					.build()
		}
	}

	abstract fun injectComponent()

	override fun showLoading() {
	}

	override fun hideLoading() {
	}

	override fun onError() {
	}
}