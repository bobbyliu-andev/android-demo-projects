package club.bobbychangliu.usercenter.injection.component

import club.bobbychangliu.baselibrary.injection.component.ActivityComponent
import club.bobbychangliu.baselibrary.injection.scope.ActivityScope
import club.bobbychangliu.baselibrary.injection.scope.PerComponentScope
import club.bobbychangliu.usercenter.injection.module.UserModule
import club.bobbychangliu.usercenter.ui.RegisterActivity
import dagger.Component

@PerComponentScope
@Component(dependencies = [ActivityComponent::class], modules = arrayOf(UserModule::class))
interface UserComponent {
    fun inject(activity: RegisterActivity)
}