package club.bobbychangliu.baselibrary.injection.component

import android.app.Activity
import android.content.Context
import club.bobbychangliu.baselibrary.injection.module.ActivityModule
import club.bobbychangliu.baselibrary.injection.module.AppModule
import club.bobbychangliu.baselibrary.injection.module.LifecycleProviderModule
import club.bobbychangliu.baselibrary.injection.scope.ActivityScope
import com.trello.rxlifecycle2.LifecycleProvider
import dagger.Component
import javax.inject.Singleton

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class, LifecycleProviderModule::class))
interface ActivityComponent {
    fun activity(): Activity
    fun lifecycleProvider(): LifecycleProvider<*>
    fun context(): Context
}