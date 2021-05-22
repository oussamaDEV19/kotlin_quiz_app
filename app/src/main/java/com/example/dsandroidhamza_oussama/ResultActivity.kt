package com.example.dsandroidhamza_oussama

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dsandroidhamza_oussama.databinding.ActivityResultBinding
import com.example.dsandroidhamza_oussama.models.Question
import com.example.dsandroidhamza_oussama.recyclers.RecyclerCumulActivity

@Suppress("UNCHECKED_CAST")
class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding
    private var attemptedQuestions = 0
    private var correctAnswers = 0
    private var negativeMarks = 0
    lateinit var mediaPlayer: MediaPlayer
    lateinit var mediaPlayer2: MediaPlayer
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        attemptedQuestions = 0
        correctAnswers = 0
        negativeMarks = 0

        mediaPlayer = MediaPlayer.create(this, R.raw.click_btn)
        mediaPlayer2 = MediaPlayer.create(this, R.raw.result)

        mediaPlayer2.start()

        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        val questionMap:HashMap<Question, Int> = intent.getSerializableExtra("questionMap") as HashMap<Question, Int>
        for (v in questionMap.keys){
            if (questionMap[v]!=-1){
                attemptedQuestions++
                if ((v.answers!![questionMap[v]!!].score).toString() == "1"){
                    correctAnswers++
                }
                else{
                    negativeMarks++
                }
            }else{
                negativeMarks++
            }
        }

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        setContentView(view)

        binding.btnCumul.setOnClickListener {
            mediaPlayer.start()
            val intent = Intent(this, RecyclerCumulActivity::class.java)
            startActivity(intent)
        }

        binding.txtScore.text = (correctAnswers*10).toString() + "%"
        binding.txtAttemptedQuestions.text = "Attempted Questions\n$attemptedQuestions"
        binding.txtCorrectAnswers.text = "Correct Answer\n$correctAnswers"
        binding.txtNegativeAnswers.text = "Negative Marks\n$negativeMarks"

        if(correctAnswers >= 7){
            binding.state.text = "Success"
            binding.stateImg.setImageResource(R.drawable.ic_success)
        }else{
            binding.state.text = "Failed"
            binding.stateImg.setImageResource(R.drawable.ic_failure)
        }

        //Inserting Record To Database
        when(idChapter){
                1->{DataBaseHandler(this).insertUserQuizLine(userName,"Introduction",correctAnswers)}
                2->{DataBaseHandler(this).insertUserQuizLine(userName,"Listes",correctAnswers)}
                3->{DataBaseHandler(this).insertUserQuizLine(userName, "Layouts",correctAnswers)}
                4->{DataBaseHandler(this).insertUserQuizLine(userName, "Recycler View",correctAnswers)}
                5->{DataBaseHandler(this).insertUserQuizLine(userName,"Intent" ,correctAnswers)}
                6->{DataBaseHandler(this).insertUserQuizLine(userName,"Bundel" ,correctAnswers)}
                7->{DataBaseHandler(this).insertUserQuizLine(userName,"Sqlite" ,correctAnswers)}
                8->{DataBaseHandler(this).insertUserQuizLine(userName, "Fragments",correctAnswers)}

        }

        binding.progressBar.apply {
            progressMax = 100f
            setProgressWithAnimation(correctAnswers.toFloat()*10,1000)
            progressBarWidth = 10f
            backgroundProgressBarWidth = 2f
            progressBarColor = Color.GREEN
            roundBorder = true
        }


        val backToChapters = binding.backToChapters

        backToChapters.setOnClickListener{
            questionMap.clear()
            attemptedQuestions=0
            negativeMarks=0
            correctAnswers=0

            mediaPlayer.start()

            val intent = Intent(this, ChaptersActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onPause() {
        super.onPause()
        val questionMap:HashMap<Question, Int> = intent.getSerializableExtra("questionMap") as HashMap<Question, Int>
        questionMap.clear()
    }
}