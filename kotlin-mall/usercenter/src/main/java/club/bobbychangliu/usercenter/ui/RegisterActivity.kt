package club.bobbychangliu.usercenter.ui

import android.os.Bundle
import club.bobbychangliu.baselibrary.common.AppManager
import club.bobbychangliu.baselibrary.ext.getString
import club.bobbychangliu.baselibrary.ui.BaseMvpActivity
import club.bobbychangliu.usercenter.R
import club.bobbychangliu.usercenter.injection.component.DaggerUserComponent
import club.bobbychangliu.usercenter.injection.module.UserModule
import club.bobbychangliu.usercenter.presenter.RegisterPresenter
import club.bobbychangliu.usercenter.presenter.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {

	private var pressedTime: Long = 0

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }

    override fun onRegisterResult(result: String) {
        toast(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mBtnRegister.setOnClickListener { mPresenter.register(mEtMobile.getString(), mEtPassword.getString(), mEtVerifyCode.getString())}
    }

	override fun onBackPressed() {
		val time = System.currentTimeMillis()
		if (time - pressedTime > 2000) {
			toast("Press again to exit app.")
			pressedTime = time
		} else {
			AppManager.instance.exitApp(this)
		}
	}
}
