package club.bobbychangliu.baselibrary.ui

import club.bobbychangliu.baselibrary.presenter.BasePresenter
import club.bobbychangliu.baselibrary.presenter.BaseView

abstract class BaseMvpFragment<T: BasePresenter<*>> : BaseFragment(), BaseView {
}