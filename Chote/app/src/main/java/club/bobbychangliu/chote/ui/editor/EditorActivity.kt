package club.bobbychangliu.chote.ui.editor

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import club.bobbychangliu.chote.R
import kotlinx.android.synthetic.main.activity_editor.*

class EditorActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_editor)
		setSupportActionBar(toolbar)
		mFabTemp.setOnClickListener { view ->
			Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
					.setAction("Action", null).show()
		}
		supportActionBar?.apply {
			setDisplayHomeAsUpEnabled(true)
		}

	}
}
