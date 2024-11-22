package com.indri.noteapp_mi2a.screen

import android.os.Bundle

import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.indri.noteapp_mi2a.R
import com.indri.noteapp_mi2a.databinding.ActivityAddNoteBinding
import com.indri.noteapp_mi2a.helper.NoteDatabaseHelper
import com.indri.noteapp_mi2a.model.ModelNote


class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: NoteDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)

        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()
            val note = ModelNote(0, title, content)
            db.insertNote(note)
            finish()
            Toast.makeText(this, "Catatan disimpan", Toast.LENGTH_SHORT).show()
        }

//        enableEdgeToEdge()
//        setContentView(R.layout.activity_add_note)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }
}