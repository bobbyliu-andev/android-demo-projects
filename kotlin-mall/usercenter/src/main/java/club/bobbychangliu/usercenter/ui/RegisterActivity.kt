package club.bobbychangliu.usercenter.ui

import android.os.Bundle
import android.view.View
import club.bobbychangliu.baselibrary.common.AppManager
import club.bobbychangliu.baselibrary.ext.getString
import club.bobbychangliu.baselibrary.ext.isNotNullOrEmpty
import club.bobbychangliu.baselibrary.ui.BaseMvpActivity
import club.bobbychangliu.usercenter.R
import club.bobbychangliu.usercenter.injection.component.DaggerUserComponent
import club.bobbychangliu.usercenter.injection.module.UserModule
import club.bobbychangliu.usercenter.presenter.RegisterPresenter
import club.bobbychangliu.usercenter.presenter.RegisterView
import com.kotlin.base.widgets.DefaultTextWatcher
import com.kotlin.base.widgets.VerifyButton
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView, View.OnClickListener {

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

		initViews()

		mBtnVerifyCode.setOnVerifyBtnClick(object : VerifyButton.OnVerifyBtnClick {
			override fun onClick() {

				toast("Getting V-Code")
			}
		})

		mBtnVerifyCode.setOnClickListener {
			mBtnVerifyCode.requestSendVerifyNumber()
		}

	}

	override fun onClick(v: View) {
		when (v.id) {
			R.id.mBtnVerifyCode -> {
				mBtnVerifyCode.requestSendVerifyNumber()
			}

			R.id.mBtnRegister -> {
				mPresenter.register(mEtMobile.getString(), mEtPassword.getString(), mEtVerifyCode.getString())
			}

		}
	}

	private fun initViews() {
		mBtnRegister.setOnClickListener(this)
		mBtnVerifyCode.setOnClickListener(this)

		listOf(mEtMobile, mEtConfirmPassword, mEtPassword, mEtVerifyCode).forEach {
			it.apply { addTextChangedListener(object : DefaultTextWatcher() {
				override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
					mBtnRegister.isEnabled = isBtnEnabled()
				}
			}) }
		}
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

	fun isBtnEnabled(): Boolean {
		return mEtMobile.isNotNullOrEmpty().and(
				mEtPassword.isNotNullOrEmpty().and(
						mEtConfirmPassword.isNotNullOrEmpty().and(
								mEtVerifyCode.isNotNullOrEmpty()
						)
				)
		)
	}
}
