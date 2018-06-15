package club.bobbychangliu.usercenter.data.protocol

data class RegisterReq(val mobile: String, val pwd: String, val verifyCode: String)