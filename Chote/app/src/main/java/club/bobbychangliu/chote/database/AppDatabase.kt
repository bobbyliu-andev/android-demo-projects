package club.bobbychangliu.chote.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import club.bobbychangliu.chote.database.helper.DateConverter

@Database(entities = [NoteEntity::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
	companion object {
		const val DATABASE_NAME = "AppDatabase.db"
		private var instance: AppDatabase? = null
		val LOCK = Any()

		fun getInstance(ctx: Context): AppDatabase {
			if (instance == null) {
				synchronized(LOCK) {
					if (instance == null) {
						instance = Room.databaseBuilder(ctx, AppDatabase::class.java, DATABASE_NAME).build()
					}
				}
			}

			return instance!!
		}
	}

	abstract fun noteDao(): NoteDao
}