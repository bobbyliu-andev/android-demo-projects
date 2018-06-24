package club.bobbychangliu.baselibrary.data.protocol

class BaseResponse<out T>(val status: Int, val message: String, val data: T)
