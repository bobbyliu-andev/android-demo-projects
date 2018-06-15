package club.bobbychangliu.baselibrary.ui

import android.os.Bundle
import club.bobbychangliu.baselibrary.common.BaseApplication
import club.bobbychangliu.baselibrary.injection.component.ActivityComponent
import club.bobbychangliu.baselibrary.injection.component.DaggerActivityComponent
import club.bobbychangliu.baselibrary.injection.module.ActivityModule
import club.bobbychangliu.baselibrary.injection.module.LifecycleProviderModule
import club.bobbychangliu.baselibrary.presenter.BasePresenter
import club.bobbychangliu.baselibrary.presenter.BaseView
import javax.inject.Inject

// *** the use of *
open class BaseMvpActivity<T: BasePresenter<*>> : BaseActivity(), BaseView {



    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityInjection()
    }

    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onError() {

    }

}