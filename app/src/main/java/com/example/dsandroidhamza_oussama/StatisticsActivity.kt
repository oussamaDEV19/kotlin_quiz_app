package com.example.dsandroidhamza_oussama

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dsandroidhamza_oussama.databinding.ActivityStatisticsBinding

class StatisticsActivity : AppCompatActivity() {
    lateinit var binding: ActivityStatisticsBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStatisticsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbh = DataBaseHandler(this)



        val first = binding.first
        val second = binding.second
        val third = binding.third

        /**
         * This Function Returns the Top 3 Scores From The Database
         */
        val data: ArrayList<String> = dbh.getMaxScore()

        if(data.isNotEmpty()){
            first.text = data[0] + " [ "+ data[1] +" pts ]"
            second.text = data[2] + " [ "+ data[3] +" pts ]"
            third.text = data[4] + " [ "+ data[5] +" pts ]"
        }else{
            first.text = "none data"
            second.text = "none data"
            third.text = "none data"
        }
    }
}