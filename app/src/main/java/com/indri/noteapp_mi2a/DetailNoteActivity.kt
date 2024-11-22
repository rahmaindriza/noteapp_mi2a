package com.indri.noteapp_mi2a

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.indri.noteapp_mi2a.databinding.ActivityDetailNoteBinding

class DetailNoteActivity : AppCompatActivity() {

    private lateinit var txtDetailJudul : TextView
    private lateinit var txtDetailNote : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_note)

        txtDetailNote = findViewById(R.id.txtDetailItemIsiNotes)
        txtDetailJudul = findViewById(R.id.txtDetailItemJudul)

        val judul = intent.getStringExtra("title")
        val kontent = intent.getStringExtra("content")

        txtDetailNote.text = kontent
        txtDetailJudul.text = judul


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}