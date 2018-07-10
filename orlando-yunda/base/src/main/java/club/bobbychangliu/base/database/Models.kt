package club.bobbychangliu.base.database

import java.util.*
import kotlin.collections.ArrayList

data class Order(
		val id: String = "",
		val barcode: String = "",
		val price: Double = 0.0,
		val agent: String = "",
		val createdAt: Date = Date(),
		val pickedAt: Date = Date(),
		val memo: String = "")

data class Agent(val name: String = "", var orders: ArrayList<String> = arrayListOf())