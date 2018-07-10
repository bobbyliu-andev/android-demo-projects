package club.bobbychangliu.chote.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
data class NoteEntity(
		@PrimaryKey(autoGenerate = true)
		val id: Int,
		val date: Date,
		val text: String) {
	@Ignore
	constructor() : this(0, Date(), "")

	@Ignore
	constructor(date: Date, note: String) : this(0, date, note)
}