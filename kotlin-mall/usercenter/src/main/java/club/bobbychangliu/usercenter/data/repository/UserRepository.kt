package club.bobbychangliu.usercenter.data.repository

import club.bobbychangliu.baselibrary.data.net.RetrofitFactory
import club.bobbychangliu.baselibrary.data.protocol.BaseResponse
import club.bobbychangliu.usercenter.data.api.UserApi
import club.bobbychangliu.usercenter.data.protocol.RegisterReq
import io.reactivex.Observable
import javax.inject.Inject

class UserRepository @Inject constructor() {

    fun register(mobile: String, pwd: String, verifyCode: String): Observable<BaseResponse<String>> {

        return RetrofitFactory.instance.create(UserApi::class.java)
                .register(RegisterReq(mobile, pwd, verifyCode))
    }
}