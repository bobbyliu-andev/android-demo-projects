package club.bobbychangliu.orlandoyunda.ui.main

import android.app.Activity
import android.app.IntentService
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import club.bobbychangliu.base.BaseActivity
import club.bobbychangliu.base.BasePresenter
import club.bobbychangliu.base.BaseView
import club.bobbychangliu.base.database.Order
import club.bobbychangliu.base.database.Agent
import club.bobbychangliu.base.database.firebase.FireAuth
import club.bobbychangliu.base.database.firebase.FirestoreUtil
import club.bobbychangliu.base.utils.toast
import club.bobbychangliu.orlandoyunda.R
import club.bobbychangliu.orlandoyunda.ui.ScannerActivity
import club.bobbychangliu.orlandoyunda.ui.add.AddOrderActivity
import club.bobbychangliu.orlandoyunda.utils.Constants
import kotlinx.android.synthetic.main.activity_operator_main.*
import java.util.*

class OperatorMainActivity : BaseActivity<OperatorPresenter>(), OperatorView {

	var tempCode = ""
	var mAdapter: OperatorMainAdapter = OperatorMainAdapter(arrayListOf())

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_operator_main)

		mPresenter = OperatorPresenter(this)
		mRcvOrders.layoutManager = LinearLayoutManager(this)
		mRcvOrders.adapter = mAdapter

		// todo: Check BuildConfig.FLAVOR
		if (FireAuth.auth.currentUser == null) {
			FireAuth.signIn(getString(R.string.username), getString(R.string.pwd)) {
				isSuccessful ->
				if (isSuccessful) {
					mPresenter.loadOrders()
				}
			}
		} else {
			FireAuth.updateUser {
				mPresenter.loadOrders()
			}
		}
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {

		when (item.itemId) {
			// todo: check permission
			R.id.mMnAdd -> toScanner()
		}

		return super.onOptionsItemSelected(item)
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)

		if (resultCode != Activity.RESULT_OK) {
			toast("Failed to get result")
			return
		}

		when (requestCode) {
			Constants.REQUEST_SCAN -> {
				val code = data?.getStringExtra(Constants.EXTRA_SCAN_RESULT)
				if (code.isNullOrEmpty()) {
					toast("Scan returns null or empty")
				} else {
					// todo: remove with parcelable
					tempCode = code!!
					toAddItem(tempCode)
				}
			}

			Constants.REQUEST_ADD_ORDER -> {
				val price = data?.getDoubleExtra(Constants.EXTRA_ADD_PRICE, 0.0) ?: return
				var memo: String? = null
				if (data?.extras.containsKey(Constants.EXTRA_ADD_MEMO)) {
					memo = data?.getStringExtra(Constants.EXTRA_ADD_MEMO)
				}
				mPresenter.addItem(tempCode, price, memo ?: "")
			}
		}
	}

	private fun toScanner() {
		val intent = Intent(this, ScannerActivity::class.java)
		startActivityForResult(intent, Constants.REQUEST_SCAN)
	}

	private fun toAddItem(code: String) {
		val intent = Intent(this, AddOrderActivity::class.java)
				.putExtra(Constants.EXTRA_SCAN_RESULT, code)
		startActivityForResult(intent, Constants.REQUEST_ADD_ORDER)
	}

	override fun updateOrdersList(orders: List<Order>) {
		mAdapter.orders = orders
		mAdapter.notifyDataSetChanged()
	}
}

interface OperatorView : BaseView {
	fun updateOrdersList(orders: List<Order>)
}

class OperatorPresenter(view: OperatorView) : BasePresenter<OperatorView>(view) {
	// todo: add
	fun addItem(barcode: String, price: Double, memo: String) {
		val username = FireAuth.mAgent.name
		val newOrder = Order(barcode, price, username, Date(), Date(), memo)

		FirestoreUtil.saveOrder(newOrder) {
			orderId ->
			// todo: update user orders
			FirestoreUtil.addAgentOrder(orderId) {
				if (it) {
					loadOrders()
				} else {
					toast("Save order failed.")
				}
			}
		}
	}

	fun loadOrders() {
		// todo: update list
		FirestoreUtil.loadAllPostedOrders() {
			orders ->
			mView.updateOrdersList(orders)
		}
	}
}
