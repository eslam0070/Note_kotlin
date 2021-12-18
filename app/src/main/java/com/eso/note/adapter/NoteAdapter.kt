package com.eso.note.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eso.note.R
import com.eso.note.model.Note

class NoteAdapter(
    val context: Context,
    val noteClickInterface: NoteClickInterface,
    val noteClickDeleteInterface: NoteClickDeleteInterface
) :
    RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private val allNotes = ArrayList<Note>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleNote.text = allNotes[position].noteTitle
        holder.timeNote.text = "Last Updated : "+allNotes[position].timeStamp

        holder.deleteNote.setOnClickListener {
            noteClickDeleteInterface.onNoteDeleteClick(allNotes[position])
        }

        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes[position])
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val titleNote = itemView.findViewById<TextView>(R.id.titleNote)
        val timeNote = itemView.findViewById<TextView>(R.id.timeNote)
        val deleteNote = itemView.findViewById<ImageView>(R.id.deleteNote)
    }
    interface NoteClickInterface {
        fun onNoteClick(note: Note)
    }

    interface NoteClickDeleteInterface {
        fun onNoteDeleteClick(note: Note)
    }
}