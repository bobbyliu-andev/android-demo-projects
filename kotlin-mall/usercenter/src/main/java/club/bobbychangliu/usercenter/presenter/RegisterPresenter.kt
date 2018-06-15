package club.bobbychangliu.usercenter.presenter

import club.bobbychangliu.baselibrary.ext.execute
import club.bobbychangliu.baselibrary.presenter.BasePresenter
import club.bobbychangliu.baselibrary.rx.BaseSubscriber
import club.bobbychangliu.usercenter.service.UserService
import javax.inject.Inject

class RegisterPresenter @Inject constructor() : BasePresenter<RegisterView>() {

    @Inject
    lateinit var userService: UserService

    fun register(mobile: String, pwd: String, verifyCode: String) {
        // todo: business logic, model
        userService.register(mobile, pwd, verifyCode)
                .execute(object : BaseSubscriber<Boolean>() {
                    override fun onNext(t: Boolean) {
                        super.onNext(t)
                        mView.onRegisterResult(t)
                    }
                }, lifecycleProvider)
    }
}