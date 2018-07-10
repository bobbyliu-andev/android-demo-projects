package club.bobbychangliu.orlandoyunda.ui.main

import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import club.bobbychangliu.base.BaseApplication
import club.bobbychangliu.base.database.Order
import club.bobbychangliu.base.database.firebase.FirestoreUtil
import club.bobbychangliu.base.utils.toast
import club.bobbychangliu.orlandoyunda.R
import kotlinx.android.synthetic.main.operator_main_cell.view.*

class OperatorMainAdapter(var orders: List<Order>) : RecyclerView.Adapter<OperatorMainAdapter.ViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.operator_main_cell, parent, false)
		return ViewHolder(view)
	}

	override fun getItemCount(): Int {
		return orders.size
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(orders[position])
	}


	inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

		fun bind(order: Order) {
			itemView.apply {
				mTvBarcode.text = order.barcode
				mTvPrice.text = "$${order.price}"
				mTvCreatedTime.text = order.createdAt.toString()
				mTvMemo.text = order.memo
				mBtnDelete.setOnClickListener {
					Snackbar.make(it, "Delete ${order.barcode}?", Snackbar.LENGTH_LONG).setAction("Confirm") {
						FirestoreUtil.deleteOrder(order.id) {
							orders = orders.filter { it.id != order.id}
							notifyItemRemoved(adapterPosition)
						}
					}.setActionTextColor(ContextCompat.getColor(BaseApplication.ctx, android.R.color.holo_red_dark))
							.show()
					mSrl.close(true)
				}
				mBtnEdit.setOnClickListener {
					toast("Edit function to be added")
				}
			}
		}
	}
}