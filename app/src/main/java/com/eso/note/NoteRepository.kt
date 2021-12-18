package com.eso.note

import androidx.lifecycle.LiveData
import com.eso.note.db.NotesDao
import com.eso.note.model.Note

class NoteRepository (private val notesDao:NotesDao){
    val allNotes : LiveData<List<Note>> = notesDao.getAllNote()

    suspend fun insert(note: Note){
        notesDao.insert(note)
    }

    suspend fun update(note: Note){
        notesDao.update(note)
    }

    suspend fun delete(note: Note){
        notesDao.delete(note)
    }
}