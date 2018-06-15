package club.bobbychangliu.baselibrary.presenter

import com.trello.rxlifecycle2.LifecycleProvider
import javax.inject.Inject

// BaseView generic
open class BasePresenter<T: BaseView> {
    lateinit var mView: T

    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>
}