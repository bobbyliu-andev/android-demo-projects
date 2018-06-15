package club.bobbychangliu.usercenter.data.api

import club.bobbychangliu.baselibrary.data.protocol.BaseResponse
import club.bobbychangliu.usercenter.data.protocol.RegisterReq
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("userCenter/register")
    fun register(@Body req: RegisterReq): Observable<BaseResponse<String>>
}