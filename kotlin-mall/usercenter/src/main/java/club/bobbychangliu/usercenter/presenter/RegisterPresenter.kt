package club.bobbychangliu.usercenter.presenter

import android.content.Context
import android.widget.Toast
import club.bobbychangliu.baselibrary.ext.execute
import club.bobbychangliu.baselibrary.ext.isNetWorkAvailable
import club.bobbychangliu.baselibrary.presenter.BasePresenter
import club.bobbychangliu.baselibrary.rx.BaseSubscriber
import club.bobbychangliu.usercenter.service.UserService
import javax.inject.Inject

class RegisterPresenter @Inject constructor() : BasePresenter<RegisterView>() {

    @Inject
    lateinit var userService: UserService

	@Inject
	lateinit var ctx: Context

    fun register(mobile: String, pwd: String, verifyCode: String) {
        // todo: business logic, model

		if (!ctx.isNetWorkAvailable()) {
			Toast.makeText(ctx, "No internet!", Toast.LENGTH_SHORT).show()
		}

		mView.showLoading()

        userService.register(mobile, pwd, verifyCode)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        if (t)
                        mView.onRegisterResult("Registered!")
                    }
                }, lifecycleProvider)
    }
}