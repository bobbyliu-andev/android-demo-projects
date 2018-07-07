package club.bobbychangliu.baselibrary.rx

import club.bobbychangliu.baselibrary.presenter.BaseView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

open class BaseSubscriber<T>(val baseView: BaseView) : Observer<T> {
    override fun onComplete() {
		baseView.hideLoading()
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
		baseView.hideLoading()

		if (e is BaseException) {
			baseView.onError(e.msg)
		}
    }
}