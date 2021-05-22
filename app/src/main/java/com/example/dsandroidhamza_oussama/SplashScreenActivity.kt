package com.example.dsandroidhamza_oussama

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.dsandroidhamza_oussama.databinding.ActivityResultBinding
import com.example.dsandroidhamza_oussama.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lateinit var mediaPlayer: MediaPlayer
        mediaPlayer = MediaPlayer.create(this , R.raw.intro)
        mediaPlayer.start()
        val imgg = binding.logo
        imgg.alpha = 0f
        imgg.animate().setDuration(2500).alpha(1f).withEndAction{
            val i = Intent(this , MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in ,  android.R.anim.fade_out)
            finish()
        }

    }
}