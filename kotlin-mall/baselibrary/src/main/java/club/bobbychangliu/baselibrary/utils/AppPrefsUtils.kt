package club.bobbychangliu.baselibrary.utils

import android.content.Context
import android.content.SharedPreferences
import club.bobbychangliu.baselibrary.common.BaseApplication

// shared pref
object AppPrefsUtils {
	private val sp: SharedPreferences = BaseApplication.context.getSharedPreferences("kt_mall_pref", Context.MODE_PRIVATE)
	private val ed: SharedPreferences.Editor

	init {
		ed = sp.edit()
	}

}