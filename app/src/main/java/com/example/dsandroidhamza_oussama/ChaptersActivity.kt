package com.example.dsandroidhamza_oussama

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dsandroidhamza_oussama.databinding.ActivityChaptersBinding

class ChaptersActivity : AppCompatActivity() {
    private lateinit  var binding: ActivityChaptersBinding
    lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        binding =  ActivityChaptersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val chapter8 = binding.qcmSqlite
        val chapter7 = binding.qcmBundle
        val chapter6 = binding.qcmListe
        val chapter5 = binding.qcmCycleView
        val chapter4 = binding.qcmLayouts
        val chapter3 = binding.qcmFragments
        val chapter2 = binding.qcmIntent
        val chapter1 = binding.qcmIntroduction
        val backHome = binding.backHome

        mediaPlayer = MediaPlayer.create(this , R.raw.click_btn)


        backHome.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            mediaPlayer.start()
            startActivity(intent)
        }

        chapter1.setOnClickListener{
            questionMap  =  LinkedHashMap()
            val intent = Intent(this, QuestionFragmentActivity::class.java)
            mediaPlayer.start()
            intent.putExtra("chapter",1)
            startActivity(intent)
        }
        chapter2.setOnClickListener{
            questionMap  =  LinkedHashMap()
            //Intent
            val intent = Intent(this, QuestionFragmentActivity::class.java)
            mediaPlayer.start()
            intent.putExtra("chapter",5)
            startActivity(intent)
        }
        chapter3.setOnClickListener{
            questionMap  =  LinkedHashMap()
            //Fragment
            val intent = Intent(this, QuestionFragmentActivity::class.java)
            intent.putExtra("chapter",7)
            mediaPlayer.start()
            startActivity(intent)
        }
        chapter4.setOnClickListener{
            questionMap  =  LinkedHashMap()
            //Layouts
            val intent = Intent(this, QuestionFragmentActivity::class.java)
            mediaPlayer.start()
            intent.putExtra("chapter",3)
            startActivity(intent)
        }
        chapter5.setOnClickListener{
            questionMap  =  LinkedHashMap()
            //Recycler
            val intent = Intent(this, QuestionFragmentActivity::class.java)
            mediaPlayer.start()
            intent.putExtra("chapter",6)
            startActivity(intent)
        }
        chapter6.setOnClickListener{
            questionMap  =  LinkedHashMap()
            //Lists
            val intent = Intent(this, QuestionFragmentActivity::class.java)
            mediaPlayer.start()
            intent.putExtra("chapter",2)
            startActivity(intent)
        }
        chapter7.setOnClickListener{
            questionMap  =  LinkedHashMap()
            //Bundels
            val intent = Intent(this, QuestionFragmentActivity::class.java)
            mediaPlayer.start()
            intent.putExtra("chapter",4)
            startActivity(intent)
        }
        chapter8.setOnClickListener{
            questionMap  =  LinkedHashMap()
            //Sqlite
            val intent = Intent(this, QuestionFragmentActivity::class.java)
            mediaPlayer.start()
            intent.putExtra("chapter",8)
            startActivity(intent)
        }

    }
}