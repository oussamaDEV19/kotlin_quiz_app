package com.example.dsandroidhamza_oussama

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dsandroidhamza_oussama.databinding.ActivityHomeBinding
import com.example.dsandroidhamza_oussama.recyclers.RecyclerRankingActivity


class HomeActivity : AppCompatActivity() {
    private lateinit  var binding: ActivityHomeBinding
    lateinit var mediaPlayer: MediaPlayer
    lateinit var mediaPlayer2: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        binding =  ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val home = binding.home
        val chapters = binding.chapters
        val rateUs = binding.rateUs
        val ranking = binding.ranking
        val statistics = binding.analytics
        val logout = binding.logout

        mediaPlayer = MediaPlayer.create(this , R.raw.click_btn)
        mediaPlayer2 = MediaPlayer.create(this , R.raw.rank_app)



        home.setOnClickListener{
            Toast.makeText(applicationContext,  "Home", Toast.LENGTH_SHORT).show()
        }
        chapters.setOnClickListener{
            Toast.makeText(applicationContext, "Chapters", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ChaptersActivity::class.java)
            mediaPlayer.start()
            startActivity(intent)
        }
        rateUs.setOnClickListener{
            Toast.makeText(applicationContext, "Rate Us", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, RateUsActivity::class.java)
            mediaPlayer.start()
            startActivity(intent)
        }
        ranking.setOnClickListener{
            Toast.makeText(applicationContext, "Ranking", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, StatisticsActivity::class.java)
            mediaPlayer2.start()
            startActivity(intent)
        }
        statistics.setOnClickListener{
            Toast.makeText(applicationContext, "Statistics", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, RecyclerRankingActivity::class.java)
            mediaPlayer.start()
            startActivity(intent)
        }
        logout.setOnClickListener{
            Toast.makeText(applicationContext, "Log Out", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            mediaPlayer.start()
            finish()
            startActivity(intent)
        }

    }
}