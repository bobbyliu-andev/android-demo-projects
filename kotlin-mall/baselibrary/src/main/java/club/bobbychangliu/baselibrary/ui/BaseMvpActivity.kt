package club.bobbychangliu.baselibrary.ui

import android.app.Dialog
import android.os.Bundle
import club.bobbychangliu.baselibrary.common.BaseApplication
import club.bobbychangliu.baselibrary.injection.component.ActivityComponent
import club.bobbychangliu.baselibrary.injection.component.DaggerActivityComponent
import club.bobbychangliu.baselibrary.injection.module.ActivityModule
import club.bobbychangliu.baselibrary.injection.module.LifecycleProviderModule
import club.bobbychangliu.baselibrary.presenter.BasePresenter
import club.bobbychangliu.baselibrary.presenter.BaseView
import club.bobbychangliu.baselibrary.widgets.ProgressLoading
import org.jetbrains.anko.toast
import javax.inject.Inject

// *** the use of *
abstract class BaseMvpActivity<T: BasePresenter<*>> : BaseActivity(), BaseView {

    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent

	lateinit var mLoadingDialog: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityInjection()
        injectComponent()

		mLoadingDialog = ProgressLoading.create(this)
    }

    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }

    abstract fun injectComponent()

    override fun showLoading() {
		mLoadingDialog.show()
    }

    override fun hideLoading() {
		mLoadingDialog.dismiss()
    }

    override fun onError(msg: String) {
		toast(msg)
    }

}