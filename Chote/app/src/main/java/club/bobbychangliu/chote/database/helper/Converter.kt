package club.bobbychangliu.chote.database.helper

import android.arch.persistence.room.TypeConverter
import java.util.*

class DateConverter {
	@TypeConverter
	fun toDate(timestamp: Long?): Date? {
		timestamp?.let {
			timestamp ->
			return Date(timestamp)
		}
		return null
	}

	@TypeConverter
	fun toTimestamp(date: Date?): Long? {
		date?.let {
			date ->
			return date.time
		}
		return null
	}
}