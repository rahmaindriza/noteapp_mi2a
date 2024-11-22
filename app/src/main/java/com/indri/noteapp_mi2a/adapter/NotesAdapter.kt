package com.indri.noteapp_mi2a.adapter

import android.content.Context
import android.content.Intent
import android.health.connect.datatypes.units.Length
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.indri.noteapp_mi2a.DetailNoteActivity
import com.indri.noteapp_mi2a.R
import com.indri.noteapp_mi2a.helper.NoteDatabaseHelper
import com.indri.noteapp_mi2a.model.ModelNote
import kotlinx.coroutines.NonDisposableHandle.parent

class NotesAdapter(
    private var notes : List<ModelNote>,
    context : Context
) :RecyclerView.Adapter<NotesAdapter.NoteViewHolder>(){

    private val db : NoteDatabaseHelper = NoteDatabaseHelper(context)

    class NoteViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView) {
        val txtItemTitle : TextView = itemView.findViewById(R.id.txtItemJudul)
        val txtItemContent : TextView = itemView.findViewById(R.id.txtItemIsiNotes)
        val cardNotes : CardView = itemView.findViewById(R.id.cardNote)
        val btnEdit : ImageView = itemView.findViewById(R.id.btnEdit)
        val btnDelete : ImageView = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note,
            parent,false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val noteData = notes[position]
        holder.txtItemTitle.text = noteData.title
        holder.txtItemContent.text = noteData.content
        holder.cardNotes.setOnClickListener(){
            val intent = Intent(holder.itemView.context,DetailNoteActivity::class.java)
            intent.putExtra("title",noteData.title)
            intent.putExtra("content",noteData.content)

            holder.itemView.context.startActivity(intent)
        }

        holder.btnDelete.setOnClickListener(){
            AlertDialog.Builder(holder.itemView.context).apply {
                setTitle("Confirmation")
                setMessage("Do yo want to continue")
                setIcon(R.drawable.beseline_delete_24)

                setPositiveButton("yes"){
                        dialogInterface, i->
                    db.deleteNote(noteData.id)
                    refreshData(db.getAllNotes())
                    Toast.makeText(holder.itemView.context,"Note Berhasil dihapus",
                        Toast.LENGTH_SHORT).show()
                    dialogInterface.dismiss()
                }
                setNeutralButton("No"){
                        dialogInterface, i->
                    dialogInterface.dismiss()
                }
            }.show() //menampilkan alert dialog
        }
    }



    //fitur untuk auto refresh data

    fun refreshData(newNotes : List<ModelNote>){
        notes = newNotes
        notifyDataSetChanged()
    }
}