package club.bobbychangliu.orlandoyunda.ui.add

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import club.bobbychangliu.base.utils.getDouble
import club.bobbychangliu.orlandoyunda.R
import club.bobbychangliu.orlandoyunda.utils.Constants
import kotlinx.android.synthetic.main.activity_add_order.*

class AddOrderActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_add_order)

		intent.getStringExtra(Constants.EXTRA_SCAN_RESULT)?.let {
			barcode ->
			mEtBarcode.setText(barcode)
	}

		mBtnSubmit.setOnClickListener {
			val price = mEtPrice.getDouble() ?: return@setOnClickListener
			val intent = Intent().putExtra(Constants.EXTRA_ADD_PRICE, price)
			if (mEtMemo.text.isNullOrEmpty().not()) {
				intent.putExtra(Constants.EXTRA_ADD_MEMO, mEtMemo.text.toString())
			}
			setResult(Activity.RESULT_OK, intent)
			finish()
		}
	}
}
