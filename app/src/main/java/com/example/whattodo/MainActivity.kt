package com.example.whattodo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whattodo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db:NoteDatabaseHelper
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)
        notesAdapter = NotesAdapter(db.getAllNotes(),this)

        binding.notesRecycleView.layoutManager = LinearLayoutManager(this)
        binding.notesRecycleView.adapter=notesAdapter

        binding.addButton.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }
    }
    override fun onResume() {
        super.onResume()
        notesAdapter.refreshData(db.getAllNotes())
    }
}