package com.indri.noteapp_mi2a

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.indri.noteapp_mi2a.adapter.NotesAdapter
import com.indri.noteapp_mi2a.databinding.ActivityMainBinding
import com.indri.noteapp_mi2a.helper.NoteDatabaseHelper
import com.indri.noteapp_mi2a.screen.AddNoteActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var noteAdapter : NotesAdapter
    private lateinit var db : NoteDatabaseHelper
    //private lateinit var binding: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)
        noteAdapter = NotesAdapter(db.getAllNotes(),this)

        binding.notesRecycleview.layoutManager = LinearLayoutManager(this)
        binding.notesRecycleview.adapter = noteAdapter

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onResume() {
        super.onResume()
        val notes = db.getAllNotes()
        noteAdapter.refreshData(notes)
    }
}