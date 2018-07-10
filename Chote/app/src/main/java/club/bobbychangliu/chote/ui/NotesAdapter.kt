package club.bobbychangliu.chote.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import club.bobbychangliu.chote.R
import club.bobbychangliu.chote.database.NoteEntity
import kotlinx.android.synthetic.main.cell_main.view.*

class NotesAdapter(var mNotes: List<NoteEntity>) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_main, parent, false)
		return ViewHolder(view)
	}

	override fun getItemCount(): Int = mNotes.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(mNotes[position])
	}

	inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		fun bind(note: NoteEntity) {
			itemView.apply {
				mTvNote.text = note.text
			}
		}
	}
}