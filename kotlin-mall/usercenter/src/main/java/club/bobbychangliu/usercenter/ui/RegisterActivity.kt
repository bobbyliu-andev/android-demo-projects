package club.bobbychangliu.usercenter.ui

import android.os.Bundle
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

    override fun onRegisterResult(result: Boolean) {
        toast("Registered!")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initInjection()


        mBtnRegister.setOnClickListener { mPresenter.register(mEtMobile.getString(), mEtPassword.getString(), mEtVerifyCode.getString())}

    }

    private fun initInjection() {
        DaggerUserComponent.builder().activityComponent(activityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }
}
