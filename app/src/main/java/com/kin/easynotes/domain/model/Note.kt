package com.kin.easynotes.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes-table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "note-name")
    val name: String,

    @ColumnInfo(name = "note-description")
    val description: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(), // Default value is the current timestamp

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis()
)