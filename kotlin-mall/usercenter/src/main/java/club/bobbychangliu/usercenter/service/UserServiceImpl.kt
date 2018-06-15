package club.bobbychangliu.usercenter.service

import club.bobbychangliu.baselibrary.rx.BaseException
import club.bobbychangliu.usercenter.data.repository.UserRepository
import io.reactivex.Observable
import javax.inject.Inject

class UserServiceImpl @Inject constructor() : UserService {

    @Inject
    lateinit var repository: UserRepository

    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {

        return repository.register(mobile, pwd, verifyCode)
                .flatMap {
                    if (it.status != 0) {
                        Observable.error<Boolean>(BaseException(it.status, it.message))
                    }
                    // if status is 0, succeed
                    Observable.just(it.status == 0)
                }
    }
}