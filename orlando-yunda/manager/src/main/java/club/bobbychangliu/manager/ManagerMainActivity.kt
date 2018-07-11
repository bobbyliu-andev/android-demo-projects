package club.bobbychangliu.manager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import club.bobbychangliu.base.database.Order
import club.bobbychangliu.base.database.firebase.FirestoreUtil
import club.bobbychangliu.base.utils.toast
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.activity_manager_main.*
import kotlinx.android.synthetic.main.main_rcv_cell.view.*
import org.jetbrains.anko.email
import kotlin.properties.Delegates

class ManagerMainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

	private val mAdapter = OrdersAdapter()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_manager_main)

		val agentArrayAdapter = ArrayAdapter.createFromResource(this, R.array.agents_array, android.R.layout.simple_spinner_item)
		agentArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
		mSpnAgent.adapter = agentArrayAdapter
		mSpnAgent.onItemSelectedListener = this

		mRcvOrders.layoutManager = LinearLayoutManager(this)
		mRcvOrders.adapter = mAdapter

		// send email
		mFabEmail.setOnClickListener {
			email(
					"usayunda.orlando@gmail.com",
					"AUTO-GENERATED FROM ORLANDO YUNDA - BARCODE SUM UP",
					AppRepository.instance.getAllBarcode()
			)
		}
	}

	override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
		val agent = parent.getItemAtPosition(position).toString()
		if (agent == "All") {
			mAdapter.mQuery = GetQuery.basic()
		} else {
			mAdapter.mQuery = GetQuery.agent(agent)
		}
	}

	override fun onNothingSelected(parent: AdapterView<*>?) {

	}
}

class OrdersAdapter(private val snapshots: ArrayList<DocumentSnapshot> = arrayListOf())
	: RecyclerView.Adapter<OrdersAdapter.ViewHolder>(), EventListener<QuerySnapshot> {

	var mQuery: Query by Delegates.observable(GetQuery.basic()) {
		_, _, newValue ->
		snapshots.clear()
		newValue.addSnapshotListener(this)
	}

	init {
		mQuery = GetQuery.basic()
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.main_rcv_cell, parent, false)
		return ViewHolder(view)
	}

	override fun getItemCount(): Int {
		return snapshots.size
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(snapshots[position])
	}

	override fun onEvent(querySnapshot: QuerySnapshot?, p1: FirebaseFirestoreException?) {

		// todo: consider querySnapshot.documentChanges.ADDED, MODIFIED, REMOVED
		snapshots.clear()
		AppRepository.instance.clearOrders()

		querySnapshot?.documents?.forEach {
			AppRepository.instance.add(it)
			snapshots.add(it)
		}
		notifyDataSetChanged()
	}

	class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

		fun bind(snapshot: DocumentSnapshot) {
			val order = snapshot.toObject(Order::class.java)

			order?.let {
				order ->
				itemView.apply {
					mTvAgent.text = order.agent
					mTvBarcode.text = order.barcode
					mTvCreatedTime.text = order.createdAt.toString()
					mTvMemo.text = order.memo
					mTvPrice.text = "$${order.price}"
				}
			}
		}
	}
}

class AppRepository {

	companion object {
		val instance: AppRepository by lazy { AppRepository() }
	}

	val orders = arrayListOf<Order>()

	fun getAllBarcode(): String {
		var allBarcode = "Total: ${orders.size}\n\n"
		if (orders.isNotEmpty()) {
			orders.forEach {
				allBarcode += "${it.barcode}\n"
			}
		} else {
			toast("No order detected")
		}
		return allBarcode
	}

	fun add(docSnapshot: DocumentSnapshot?) {
		val order = docSnapshot?.toObject(Order::class.java)
		if (order == null) {
			toast("Error: Order found null")
		} else {
			orders.add(order!!)
		}
	}

	fun clearOrders() {
		orders.clear()
	}
}

fun String.add(addString: String): String {
	return "$this\n$addString"
}

object GetQuery {

	// basic
	fun basic(): Query {
		return FirestoreUtil.firestore
				.collection("orders")
	}

	fun agent(name: String): Query {
		return FirestoreUtil.firestore
				.collection("orders")
				.whereEqualTo("agent", name)
	}

	// order by price
	fun priceDesc(): Query {
		return FirestoreUtil.firestore
				.collection("orders")
				.orderBy("price", Query.Direction.DESCENDING)
	}

	// price range
	fun priceRange(from: Double, to: Double): Query {
		return FirestoreUtil.firestore
				.collection("orders")
				.whereLessThan("price", to)
				.whereGreaterThan("price", from)
	}
}