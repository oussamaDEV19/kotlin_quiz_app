package com.example.dsandroidhamza_oussama.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dsandroidhamza_oussama.R
import com.example.dsandroidhamza_oussama.databinding.RankingLayoutBinding

@Suppress("DEPRECATION")
class RecyclerRankingAdapter(private val chaptersNames: ArrayList<String>, private val usersNames: ArrayList<String>, private val scores: ArrayList<Int>) : RecyclerView.Adapter<RecyclerRankingAdapter.ViewHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = RankingLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(usersNames[position],chaptersNames[position],scores[position] )
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return chaptersNames.size
    }


    //the class is holding the list view
    class ViewHolder(private val myItemView: RankingLayoutBinding) : RecyclerView.ViewHolder(myItemView.root) {


        @SuppressLint("SetTextI18n")
        fun bindItems(username:String, chapterName: String, score: Int) {
            val txtUserName = myItemView.txtUserName
            val txtChapterName = myItemView.txtChapterName
            val imgg = myItemView.score

            txtUserName.text = "By $username"
            txtChapterName.text = "chp : $chapterName"

            when (score) {
                1 -> imgg.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_one))
                2 -> imgg.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_two))
                3 -> imgg.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_three))
                4 -> imgg.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_four))
                5 -> imgg.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_five))
                6 -> imgg.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_six))
                7 -> imgg.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_seven))
                8 -> imgg.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_eight))
                9 -> imgg.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_nine))
                10 -> imgg.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_ten))
                else -> imgg.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_zero))
            }
        }
    }
}