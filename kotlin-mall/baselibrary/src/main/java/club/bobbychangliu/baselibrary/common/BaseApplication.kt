package club.bobbychangliu.baselibrary.common

import android.app.Application
import club.bobbychangliu.baselibrary.injection.component.AppComponent
import club.bobbychangliu.baselibrary.injection.component.DaggerAppComponent
import club.bobbychangliu.baselibrary.injection.module.AppModule

class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        initAppInjection()
    }

    private fun initAppInjection() {
        // to add context
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}