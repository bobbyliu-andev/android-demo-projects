package club.bobbychangliu.baselibrary.rx

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

open class BaseSubscriber<T> : Observer<T> {
    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
    }
}