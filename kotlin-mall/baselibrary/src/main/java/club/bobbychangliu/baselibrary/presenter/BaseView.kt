package club.bobbychangliu.baselibrary.presenter

interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(msg: String)
}