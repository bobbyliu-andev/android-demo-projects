package club.bobbychangliu.chote

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import club.bobbychangliu.chote.database.AppDatabase
import club.bobbychangliu.chote.database.NoteDao
import club.bobbychangliu.chote.ui.main.SampleData
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

	companion object {
		const val TAG = "Junit.DatabaseTest.TAG"
		fun logi(msg: String) { Log.i(TAG, msg)}
	}

	private lateinit var mDb: AppDatabase
	private lateinit var mDao: NoteDao

	@Before
	fun createDb() {
		val appContext = InstrumentationRegistry.getTargetContext()
		mDb = Room.inMemoryDatabaseBuilder(appContext, AppDatabase::class.java).build()
		mDao = mDb.noteDao()
		mDao.insertAll(SampleData.getNotes())
		logi("create database")
	}

	@After
	fun closeDb() {
		mDb.close()
		logi("close database")
	}

	@Test
	fun createAndRetrieveNotes() {
		val count = mDao.getCount()
		logi("createAndRetrieveNotes: count=$count")
		assertEquals(SampleData.getNotes().size, count)
	}

	@Test
	fun compareStrings() {
		val original = SampleData.getNotes()[0]
		val fromDb = mDao.getNoteById(1)
		assertEquals(original.text, fromDb.text)
		assertEquals(1, fromDb.id)
	}
}
