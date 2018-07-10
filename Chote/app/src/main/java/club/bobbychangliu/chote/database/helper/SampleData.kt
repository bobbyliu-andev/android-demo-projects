package club.bobbychangliu.chote.database.helper

import club.bobbychangliu.chote.database.NoteEntity
import java.util.*

object SampleData {
	const val SAMPLE_TEXT_1 = "sample text 1"
	const val SAMPLE_TEXT_2 = "sample text 2"
	const val SAMPLE_TEXT_3 = "sample text 3 sample text 3 sample text 3sample text 3sample text 3sample text 3sample text 3sample text 3 sample text 3 sample text 3sample text 3sample text 3sample text 3sample text 3sample text 3sample text 3sample text 3sample text 3sample text 3sample text 3sample text 3sample text 3sample text 3sample text 3sample text 3sample text 3sample text 3"

	fun getDate(diff: Int): Date {
		val cal = GregorianCalendar(Locale.getDefault())
		cal.add(Calendar.MILLISECOND, diff)
		return cal.time
	}

	fun getNotes(): List<NoteEntity> {
		val notes = arrayListOf(
				NoteEntity(getDate(0), SAMPLE_TEXT_1),
				NoteEntity(getDate(-1), SAMPLE_TEXT_2),
				NoteEntity(getDate(-2), SAMPLE_TEXT_3)
		)
		return notes
	}
}