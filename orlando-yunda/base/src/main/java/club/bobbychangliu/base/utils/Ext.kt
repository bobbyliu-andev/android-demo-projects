package club.bobbychangliu.base.utils

import android.view.View
import android.widget.EditText
import android.widget.Toast
import club.bobbychangliu.base.BaseApplication

fun toast(msg: String) {
	Toast.makeText(BaseApplication.ctx, msg, Toast.LENGTH_SHORT).show()
}

fun EditText.getDouble(): Double? {
	if (text.isNullOrEmpty()) {
		this.error = "Input is required"
		return null
	}

	return text.toString().toDouble()
}
