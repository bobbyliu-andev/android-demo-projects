package club.bobbychangliu.base.database.firebase

import club.bobbychangliu.base.database.Order
import club.bobbychangliu.base.utils.toast
import com.google.firebase.firestore.FirebaseFirestore

object FirestoreUtil {
	val firestore: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

	fun saveOrder(order: Order, onResultListener: (String) -> Unit) {
		firestore.collection("orders") .add(order)
				.addOnSuccessListener {
					documentReference ->
					onResultListener(documentReference.id)
				}.addOnFailureListener {
					exception ->
					exception.printStackTrace()
					toast(exception.localizedMessage)
				}
	}

	fun addAgentOrder(orderId: String, onResultListener: (Boolean) -> Unit) {
		val uid = FireAuth.auth.currentUser?.uid ?: return
		FireAuth.mAgent.orders.add(orderId)
		firestore.collection("agents").document(uid)
				.set(FireAuth.mAgent)
				.addOnSuccessListener {
					onResultListener(true)
				}.addOnFailureListener {
					onResultListener(false)
				}
	}

	fun loadAllPostedOrders(ordersToLoad: List<String> = FireAuth.mAgent.orders, resultHolderList: ArrayList<Order> = arrayListOf(), onResultListener: (ArrayList<Order>) -> Unit) {
		// todo: check if mUser is null?
		val postCount = ordersToLoad.size
		when (postCount) {
			0 -> toast("No order to load.")
			1 -> loadOrder(ordersToLoad[0], resultHolderList) { items ->
				onResultListener(items)
			}
			else -> {
				loadOrder(ordersToLoad[postCount - 1], resultHolderList) { items ->
					loadAllPostedOrders(ordersToLoad.dropLast(1), items, onResultListener)
				}
			}
		}
	}

	fun loadOrder(id: String, resultHolderList: ArrayList<Order> = arrayListOf(), onResultListener: (ArrayList<Order>) -> Unit) {
		firestore.collection("orders").document(id).get()
				.addOnCompleteListener { task ->
					if (task.isSuccessful) {
						task.result.toObject(Order::class.java)?.let { order ->
							resultHolderList.add(order)
							onResultListener(resultHolderList)
						}
					} else {
						// todo: DEBUG prompt
						toast("Failed loading Order: $id")
					}
				}
	}
}