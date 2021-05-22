package com.example.dsandroidhamza_oussama.fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dsandroidhamza_oussama.*
import com.example.dsandroidhamza_oussama.databinding.FragmentQuestionPageBinding
import com.example.dsandroidhamza_oussama.models.Question


class QuestionPageFragment(private val position: Int, private val questions: List<Question>) : Fragment() {
    lateinit var binding: FragmentQuestionPageBinding
    lateinit var question: Question
    lateinit var mediaPlayer: MediaPlayer


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionPageBinding.inflate(inflater, container, false)
        val view = binding.root
        mediaPlayer = MediaPlayer.create(this.context , R.raw.click_qcm)
       //Toast.makeText(activity, "position is "+ position, Toast.LENGTH_LONG).show()
         question = questions[position]

        if(questionMap[question] != null){
            when (questionMap[question]) {
                0 -> binding.answer.text = "A"
                1 -> binding.answer.text = "B"
                2 -> binding.answer.text = "C"
                3 -> binding.answer.text = "D"
            }
        }else{
            questionMap[question] = -1
        }

        binding.qctNbr.setText("Q" + (position+1).toString())



        initViews(question)

        return view

    }

    private fun initViews(question: Question) {

        if(question.question?.length!! > 80){
            binding.tvQuestion.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14F);
        }
        binding.tvQuestion.text = question.question
        binding.tvOptionA.text = question.answers!![0].answer
        binding.tvOptionB.text = question.answers!![1].answer
        binding.tvOptionC.text = question.answers!![2].answer
        binding.tvOptionD.text = question.answers!![3].answer



        binding.lyOptionA.setOnClickListener {
            getResult("a")
            binding.answer.text = "A"
            chngImg(position)
        }
        binding.lyOptionB.setOnClickListener {
            getResult("b")
            binding.answer.text = "B"
            chngImg(position)
        }
        binding.lyOptionC.setOnClickListener {
            getResult("c")
            binding.answer.text = "C"
            chngImg(position)
        }
        binding.lyOption4.setOnClickListener {
            getResult("d")
            binding.answer.text = "D"
            chngImg(position)
        }


    }

    private fun getResult(selection: String) {


        when {
            selection.equals("a", ignoreCase = true) -> {
                Toast.makeText(activity, "You Choose A", Toast.LENGTH_SHORT).show()
                mediaPlayer.start()
                questionMap[question] = 0
            }
            selection.equals("b", ignoreCase = true) -> {
                Toast.makeText(activity, "You Choose B", Toast.LENGTH_SHORT).show()
                mediaPlayer.start()
                questionMap[question] = 1
            }
            selection.equals("c", ignoreCase = true) -> {
                Toast.makeText(activity, "You Choose C", Toast.LENGTH_SHORT).show()
                mediaPlayer.start()
                questionMap[question] = 2
            }
            selection.equals("d", ignoreCase = true) -> {
                Toast.makeText(activity, "You Choose D", Toast.LENGTH_SHORT).show()
                mediaPlayer.start()
                questionMap[question] = 3
            }
        }
    }

    fun chngImg(position : Int){
        var mainQuiz = (activity as QuestionFragmentActivity).binding
        when(position){
            0->{
                mainQuiz.q1.setImageResource(R.drawable.ic_here)}
            1->{
                mainQuiz.q2.setImageResource(R.drawable.ic_here)}
            2->{
                mainQuiz.q3.setImageResource(R.drawable.ic_here)}
            3->{
                mainQuiz.q4.setImageResource(R.drawable.ic_here)}
            4->{
                mainQuiz.q5.setImageResource(R.drawable.ic_here)}
            5->{
                mainQuiz.q6.setImageResource(R.drawable.ic_here)}
            6->{
                mainQuiz.q7.setImageResource(R.drawable.ic_here)}
            7->{
                mainQuiz.q8.setImageResource(R.drawable.ic_here)}
            8->{
                mainQuiz.q9.setImageResource(R.drawable.ic_here)}
            9->{
                mainQuiz.q10.setImageResource(R.drawable.ic_here)
            }

            else -> mainQuiz.q10.setImageResource(R.drawable.ic_qcm)
        }
    }


    public fun chngImg2(position : Int){
        var mainQuiz = (activity as QuestionFragmentActivity).binding
        when(position){
            0->{
                mainQuiz.q1.setImageResource(R.drawable.ic_here)
                mainQuiz.q2.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q3.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q4.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q5.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q6.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q7.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q8.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q9.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q10.setImageResource(R.drawable.ic_qcm)
            }
            1->{
                mainQuiz.q1.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q2.setImageResource(R.drawable.ic_here)
                mainQuiz.q3.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q4.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q5.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q6.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q7.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q8.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q9.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q10.setImageResource(R.drawable.ic_qcm)
            }
            2->{
                mainQuiz.q1.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q2.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q3.setImageResource(R.drawable.ic_here)
                mainQuiz.q4.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q5.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q6.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q7.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q8.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q9.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q10.setImageResource(R.drawable.ic_qcm)
            }
            3->{
                mainQuiz.q1.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q2.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q3.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q4.setImageResource(R.drawable.ic_here)
                mainQuiz.q5.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q6.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q7.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q8.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q9.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q10.setImageResource(R.drawable.ic_qcm)
            }
            4->{
                mainQuiz.q1.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q2.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q3.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q4.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q5.setImageResource(R.drawable.ic_here)
                mainQuiz.q6.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q7.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q8.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q9.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q10.setImageResource(R.drawable.ic_qcm)
            }
            5->{
                mainQuiz.q1.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q2.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q3.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q4.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q5.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q6.setImageResource(R.drawable.ic_here)
                mainQuiz.q7.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q8.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q9.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q10.setImageResource(R.drawable.ic_qcm)
            }
            6->{
                mainQuiz.q1.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q2.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q3.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q4.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q5.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q6.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q7.setImageResource(R.drawable.ic_here)
                mainQuiz.q8.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q9.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q10.setImageResource(R.drawable.ic_qcm)
            }
            7->{
                mainQuiz.q1.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q2.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q3.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q4.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q5.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q6.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q7.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q8.setImageResource(R.drawable.ic_here)
                mainQuiz.q9.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q10.setImageResource(R.drawable.ic_qcm)
            }
            8->{
                mainQuiz.q1.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q2.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q3.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q4.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q5.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q6.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q7.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q8.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q9.setImageResource(R.drawable.ic_here)
                mainQuiz.q10.setImageResource(R.drawable.ic_qcm)
            }
            9->{
                mainQuiz.q1.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q2.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q3.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q4.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q5.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q6.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q7.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q8.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q9.setImageResource(R.drawable.ic_qcm)
                mainQuiz.q10.setImageResource(R.drawable.ic_here)
            }

            else -> mainQuiz.q10.setImageResource(R.drawable.ic_qcm)
        }
    }
}