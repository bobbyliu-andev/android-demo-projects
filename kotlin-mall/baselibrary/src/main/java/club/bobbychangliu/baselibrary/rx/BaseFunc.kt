package club.bobbychangliu.baselibrary.rx

import club.bobbychangliu.baselibrary.data.protocol.BaseResponse
import io.reactivex.Observable

class BaseFunc {
    companion object {

		/**
		 *  Observable<BaseResponse<T>> -> Observable<T>
		 */
        fun <T> flatToBoolean(t: BaseResponse<T>): Observable<Boolean> {
            if (t.status != 0) return Observable.error(BaseException(t.status, t.message))

            return Observable.just(true)
        }

		// Observable<BaseResponse<T>> -> Observable<T>
		fun <T> flat(t: BaseResponse<T>): Observable<T> {
			if (t.status == 0) return Observable.just(t.data)

			return Observable.error(BaseException(t.status, t.message))
		}
    }
}

// Ext used flat
fun <T> Observable<BaseResponse<T>>.convert(): Observable<T> {
	return this.flatMap { BaseFunc.flat(it) }
}

fun <T> Observable<BaseResponse<T>>.convertBoolean(): Observable<Boolean> {
	return this.flatMap { BaseFunc.flatToBoolean(it) }
}