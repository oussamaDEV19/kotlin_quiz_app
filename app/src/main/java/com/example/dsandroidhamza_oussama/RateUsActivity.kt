package com.example.dsandroidhamza_oussama

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dsandroidhamza_oussama.databinding.ActivityRateUsBinding
import com.hsalf.smilerating.SmileRating



class RateUsActivity : AppCompatActivity() , SmileRating.OnSmileySelectionListener, SmileRating.OnRatingSelectedListener {

    lateinit var binding: ActivityRateUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.title = "Rate Us Now!"
        } catch (e: NullPointerException) {
        }

        this.binding = ActivityRateUsBinding.inflate(layoutInflater)

        setContentView(this.binding.root)


        binding.ratingView.also {

            it.setOnSmileySelectionListener(this)
            it.setOnRatingSelectedListener(this)
        }


    }

    override fun onSmileySelected(smiley: Int, reselected: Boolean) {
        when (smiley) {
            SmileRating.BAD -> Toast.makeText(this, "Bad", Toast.LENGTH_SHORT).show()
            SmileRating.GOOD -> Toast.makeText(this, "Good", Toast.LENGTH_SHORT).show()
            SmileRating.GREAT -> Toast.makeText(this, "Great", Toast.LENGTH_SHORT).show()
            SmileRating.OKAY -> Toast.makeText(this, "Okey", Toast.LENGTH_SHORT).show()
            SmileRating.TERRIBLE -> Toast.makeText(this, "Terrible", Toast.LENGTH_SHORT).show()
            SmileRating.NONE -> Toast.makeText(this, "None", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRatingSelected(level: Int, reselected: Boolean) {
        Toast.makeText(this, "Rated as: $level - $reselected", Toast.LENGTH_SHORT).show()
    }

}

