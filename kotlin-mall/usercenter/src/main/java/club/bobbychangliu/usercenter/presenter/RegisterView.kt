package club.bobbychangliu.usercenter.presenter

import club.bobbychangliu.baselibrary.presenter.BaseView

interface RegisterView : BaseView {

    fun onRegisterResult(result: Boolean)
}