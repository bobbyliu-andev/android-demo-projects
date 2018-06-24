package club.bobbychangliu.baselibrary.widgets

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import club.bobbychangliu.baselibrary.R

class ProgressLoading(ctx: Context, theme: Int) : Dialog(ctx, theme) {

	companion object {
		lateinit var mDialog: ProgressLoading

		fun create(ctx: Context): ProgressLoading {
			mDialog = ProgressLoading(ctx, R.style.LightProgressDialog)
			mDialog.setContentView(R.layout.progress_dialog)
			mDialog.setCancelable(true) // enable cancel
			mDialog.setCanceledOnTouchOutside(false)
			// display in center
			mDialog.window.attributes.gravity = Gravity.CENTER

			// dim bg
			val lp = mDialog.window.attributes
			lp.dimAmount = 0.2f

			mDialog.window.attributes = lp

			return mDialog
		}
	}
}