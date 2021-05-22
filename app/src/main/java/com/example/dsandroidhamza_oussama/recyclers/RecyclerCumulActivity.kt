package com.example.dsandroidhamza_oussama.recyclers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dsandroidhamza_oussama.adapters.RecyclerCumulAdapter
import com.example.dsandroidhamza_oussama.databinding.ActivityRecyclerCumulBinding
import com.example.dsandroidhamza_oussama.questionMap

class RecyclerCumulActivity : AppCompatActivity() {

    lateinit var binding: ActivityRecyclerCumulBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerCumulBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.cumulRecycler

        //adding a layout manager
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        //creating our adapter
        val adapter = RecyclerCumulAdapter(questionMap)

        //now adding the adapter to recyclerview
        recyclerView.adapter = adapter
    }
}