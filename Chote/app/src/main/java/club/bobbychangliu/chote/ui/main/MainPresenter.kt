package club.bobbychangliu.chote.ui.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v4.content.LocalBroadcastManager
import club.bobbychangliu.chote.ChoteApp
import club.bobbychangliu.chote.database.AppRepository
import club.bobbychangliu.chote.utils.Constants

class MainPresenter(val mView: MainActivity) {

	private var isReceiverRegistered = false

	val mReceiver = object : BroadcastReceiver() {
		override fun onReceive(context: Context, intent: Intent) {
			updateList()
		}
	}

	fun registerReceiver() {
		if (isReceiverRegistered) return
		isReceiverRegistered = true
		val filter = IntentFilter(Constants.BDC_NOTES_UPDATED)
		LocalBroadcastManager.getInstance(ChoteApp.ctx).registerReceiver(mReceiver, filter)
	}

	fun unregisterReceiver() {
		isReceiverRegistered = false
		LocalBroadcastManager.getInstance(ChoteApp.ctx).unregisterReceiver(mReceiver)
	}

	fun addSampleNotes() {
		AppRepository.instance.addSampleNotes()
	}

	fun updateList() {
		val newNotes = AppRepository.instance.mNotes
		mView.updateList(newNotes)
	}

}