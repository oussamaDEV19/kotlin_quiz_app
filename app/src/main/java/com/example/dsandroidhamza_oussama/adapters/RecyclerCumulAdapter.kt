package com.example.dsandroidhamza_oussama.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dsandroidhamza_oussama.R
import com.example.dsandroidhamza_oussama.databinding.CumulLayoutBinding
import com.example.dsandroidhamza_oussama.models.Question

/**
 * This Class Takes a Hashmap of Questions and the selected answers to show them in a recyclerView
 */
@Suppress("DEPRECATION")
class RecyclerCumulAdapter(private val questionsMap: LinkedHashMap<Question, Int>) : RecyclerView.Adapter<RecyclerCumulAdapter.ViewHolder>() {

    var questions: ArrayList<Question> = questionsMap.keys.toList() as ArrayList<Question>

    //this method is returning the view for each item in the list
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = CumulLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            //val v = LayoutInflater.from(parent.context).inflate(R.layout.cumul_layout, parent, false)

            return ViewHolder(itemBinding)
        }

        //this method is binding the data on the list
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            questionsMap[questions[position]]?.let { holder.bindItems(questions[position], it,position) }
        }

        //this method is giving the size of the list
        override fun getItemCount(): Int = questionsMap.size


    /**
     * the class is holding the list view
     */
    class ViewHolder(private val myItemView: CumulLayoutBinding) : RecyclerView.ViewHolder(myItemView.root) {

        @SuppressLint("SetTextI18n")
        fun bindItems(question: Question, response: Int, position: Int) {
            val txtQuestion = myItemView.txtQuestion
            val txtOption1 = myItemView.txtOption1
            val txtOption2 = myItemView.txtOption2
            val txtOption3 = myItemView.txtOption3
            val txtOption4 = myItemView.txtOption4
            val lgg = myItemView.lgg

            /*
            *Reseting Colors
             */
            txtOption1.setTextColor(Color.parseColor("#808080"))
            txtOption2.setTextColor(Color.parseColor("#808080"))
            txtOption3.setTextColor(Color.parseColor("#808080"))
            txtOption4.setTextColor(Color.parseColor("#808080"))

            txtQuestion.text = "- " + question.question

            txtOption1.text = "🔹 " + question.answers?.get(0)?.answer
            txtOption2.text = "🔹 " +question.answers?.get(1)?.answer
            txtOption3.text = "🔹 " +question.answers?.get(2)?.answer
            txtOption4.text = "🔹 " + question.answers?.get(3)?.answer


            itemView.findViewById<TextView>(R.id.qct_n).text = "Question  :"+ (position+1)



            when {
                question.answers?.get(0)?.score == 1 -> {
                    txtOption1.text = "🔹 " + question.answers?.get(0)?.answer + "  ✅️"
                    txtOption1.setTextColor(Color.parseColor("#3df700"))
                    lgg.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_approved))
                }
                question.answers?.get(1)?.score == 1 -> {
                    txtOption2.text = "🔹 " + question.answers?.get(1)?.answer + "  ✅️"
                    txtOption2.setTextColor(Color.parseColor("#3df700"))
                    lgg.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_approved))
                }
                question.answers?.get(2)?.score == 1 -> {
                    txtOption3.text = "🔹 " + question.answers?.get(2)?.answer + "  ✅️"
                    txtOption3.setTextColor(Color.parseColor("#3df700"))
                    lgg.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_approved))
                }
                question.answers?.get(3)?.score == 1 ->  {
                    txtOption4.text = "🔹 " + question.answers?.get(3)?.answer + "  ✅️"
                    txtOption4.setTextColor(Color.parseColor("#3df700"))
                    lgg.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_approved))
                }
            }

            when (response) {
                -1 -> {
                        lgg.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_neutral))
                }

                0 -> {
                    if (question.answers?.get(0)?.score == 0) {
                        txtOption1.text = txtOption1.text.toString() + "  ☠️"
                        txtOption1.setTextColor(Color.parseColor("#f70000"))
                        lgg.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_wrong))
                    }
                }
                1 -> {
                    if (question.answers?.get(1)?.score == 0) {
                        txtOption2.text = txtOption2.text.toString() + "  ☠️"
                        txtOption2.setTextColor(Color.parseColor("#f70000"))
                        lgg.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_wrong))
                    }
                }
                2 -> {
                    if (question.answers?.get(2)?.score == 0) {
                        txtOption3.text = txtOption3.text.toString() + "  ☠️"
                        txtOption3.setTextColor(Color.parseColor("#f70000"))
                        lgg.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_wrong))
                    }
                }
                3 -> {
                    if (question.answers?.get(3)?.score == 0) {
                        txtOption4.text = txtOption4.text.toString() + "  ☠️"
                        txtOption4.setTextColor(Color.parseColor("#f70000"))
                        lgg.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_wrong))
                    }
                }
            }
        }
    }
}
