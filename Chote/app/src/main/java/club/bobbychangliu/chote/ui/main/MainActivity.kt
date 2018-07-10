package club.bobbychangliu.chote.ui.main

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import club.bobbychangliu.chote.ui.editor.EditorActivity
import club.bobbychangliu.chote.R
import club.bobbychangliu.chote.database.NoteEntity
import club.bobbychangliu.chote.ui.NotesAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
	private val mAdapter: NotesAdapter by lazy {
		NotesAdapter(listOf())
	}
	private lateinit var mPresenter: MainPresenter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		setSupportActionBar(toolbar)
		supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimaryDark)))

		mFabAddNote.setOnClickListener { _ ->
			val intent = Intent(this, EditorActivity::class.java)
			startActivity(intent)
		}

		// init
		initViews()
	}

	private fun initViews() {
		mPresenter = MainPresenter(this)
		mPresenter.registerReceiver()
		mPresenter.updateList()
		setRcv()
	}

	private fun setRcv() {
		mRcvMain.apply {
			setHasFixedSize(true) // *** cell height fix, no auto-sizing
			layoutManager = LinearLayoutManager(this@MainActivity)
			adapter = mAdapter
		}
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		menuInflater.inflate(R.menu.menu_main, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		return when (item.itemId) {
			R.id.mMniAddSampleNotes -> addSampleData()
			else -> super.onOptionsItemSelected(item)
		}
	}

	private fun addSampleData(): Boolean {
		mPresenter.addSampleNotes()
		return true
	}

	override fun onResume() {
		super.onResume()
		mPresenter.registerReceiver()
	}

	override fun onPause() {
		super.onPause()
		mPresenter.unregisterReceiver()
	}

	fun updateList(newNotes: List<NoteEntity>) {
		mAdapter.apply {
			mNotes = newNotes
			notifyDataSetChanged()
		}
	}
}