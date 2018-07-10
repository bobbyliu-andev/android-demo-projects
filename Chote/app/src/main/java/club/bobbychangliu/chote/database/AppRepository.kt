package club.bobbychangliu.chote.database

import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import club.bobbychangliu.chote.ChoteApp
import club.bobbychangliu.chote.database.helper.SampleData
import club.bobbychangliu.chote.utils.Constants
import java.util.concurrent.Executors
import kotlin.properties.Delegates

class AppRepository {

	companion object {
		val instance: AppRepository by lazy { AppRepository() }
	}

	private val mDb: AppDatabase = AppDatabase.getInstance(ChoteApp.ctx)
	var mNotes: List<NoteEntity> by Delegates.observable(listOf()) { property, oldValue, newValue ->
		val broadcastIntent = Intent(Constants.BDC_NOTES_UPDATED)
		val lbm = LocalBroadcastManager.getInstance(ChoteApp.ctx)
		lbm.sendBroadcast(broadcastIntent)
	}

	// Executors for manipulate async, and make sure one op at a time!
	private val executor = Executors.newSingleThreadExecutor()

	init {
		executor.execute {
			mNotes = mDb.noteDao().getAll()
		}
	}

	fun addSampleNotes() {
		executor.execute {
			mDb.noteDao().apply {
				insertAll(SampleData.getNotes())
				mNotes = getAll()
			}
		}
	}
}


