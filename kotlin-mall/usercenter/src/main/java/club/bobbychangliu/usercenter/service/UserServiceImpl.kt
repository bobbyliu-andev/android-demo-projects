package club.bobbychangliu.usercenter.service

import club.bobbychangliu.baselibrary.rx.convertBoolean
import club.bobbychangliu.usercenter.data.repository.UserRepository
import io.reactivex.Observable
import javax.inject.Inject

class UserServiceImpl @Inject constructor() : UserService {

    @Inject
    lateinit var repository: UserRepository

    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {

        return repository.register(mobile, pwd, verifyCode)
                .convertBoolean()
    }
}