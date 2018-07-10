package club.bobbychangliu.chote.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface NoteDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insertNote(note: NoteEntity)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insertAll(notes: List<NoteEntity>)

	@Delete
	fun deleteNote(note: NoteEntity)

	@Query("select * from notes where id = :id")
	fun getNoteById(id: Int): NoteEntity

	@Query("select * from notes order by date desc")
	fun getAll(): List<NoteEntity>

	@Query("delete from notes")
	fun deleteAll()

	@Query("select count(*) from notes")
	fun getCount(): Int
}