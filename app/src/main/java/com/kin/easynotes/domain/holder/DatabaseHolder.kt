package com.kin.easynotes.domain.holder

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kin.easynotes.data.NoteDatabase
import com.kin.easynotes.domain.repository.NoteRepository

object DatabaseHolder {
    private lateinit var database: NoteDatabase

    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            // Add the created_at column with a default value of the current timestamp
            db.execSQL("ALTER TABLE `notes-table` ADD COLUMN `created_at` INTEGER NOT NULL DEFAULT ${System.currentTimeMillis()}")
        }
    }

    private val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE `notes-table` ADD COLUMN `updated_at` INTEGER NOT NULL DEFAULT ${System.currentTimeMillis()}")
            db.execSQL("UPDATE `notes-table` SET `updated_at` = `created_at`")
        }
    }

    val noteRepository by lazy {
        NoteRepository(noteDao = database.noteDao())
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context, NoteDatabase::class.java, "note-list.db")
            .addMigrations(MIGRATION_1_2)
            .addMigrations(MIGRATION_2_3)
            .build()
    }
}
