package com.eso.note

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eso.note.adapter.NoteAdapter
import com.eso.note.model.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteAdapter.NoteClickDeleteInterface,
    NoteAdapter.NoteClickInterface {

    lateinit var noteRv: RecyclerView
    lateinit var addFab: FloatingActionButton
    lateinit var noteAdapter: NoteAdapter
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        noteRv = findViewById(R.id.idRVNotes)
        addFab = findViewById(R.id.idFABAddNote)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)

        setRecyclerView()

    }

    private fun setRecyclerView() {
        noteAdapter = NoteAdapter(this@MainActivity, this@MainActivity, this@MainActivity)
        noteRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = noteAdapter

        }
        viewModel.allNotes.observe(this, { list ->
            list?.let { noteAdapter.updateList(it) }
        })

        addFab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this, AddActivity::class.java)
        intent.putExtra("noteType","Edit")
        intent.putExtra("noteTitle",note.noteTitle)
        intent.putExtra("noteDesc",note.noteDescription)
        intent.putExtra("noteID",note.id)
        startActivity(intent)
        finish()
    }

    override fun onNoteDeleteClick(note: Note) {
        viewModel.deleteNote(note)
    }
}