package com.example.dsandroidhamza_oussama.recyclers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dsandroidhamza_oussama.DataBaseHandler
import com.example.dsandroidhamza_oussama.adapters.RecyclerRankingAdapter
import com.example.dsandroidhamza_oussama.databinding.ActivityRecyclerRankingBinding

class RecyclerRankingActivity : AppCompatActivity() {

    lateinit var binding:ActivityRecyclerRankingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerRankingBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val recyclerView = binding.recyclerRanking

        //adding a layout manager
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        //creating our adapter
        val dbh = DataBaseHandler(this)
        val adapter = RecyclerRankingAdapter(dbh.getChapterChapterNames(),dbh.getChapterUserNames(),dbh.getChapterScores())

        //now adding the adapter to recyclerview
        recyclerView.adapter = adapter

    }
}