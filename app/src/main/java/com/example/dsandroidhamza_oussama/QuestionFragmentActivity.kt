package com.example.dsandroidhamza_oussama

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.dsandroidhamza_oussama.databinding.ActivityQuestionBinding
import com.example.dsandroidhamza_oussama.fragments.QuestionPageFragment
import com.example.dsandroidhamza_oussama.models.Question
import com.example.dsandroidhamza_oussama.suppliers.Supplier
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.LinkedHashMap


private const val NUMPAGES = 10
var questionMap = LinkedHashMap<Question, Int>()
var idChapter = 0
var userName = "ha"

class QuestionFragmentActivity : FragmentActivity() {

    lateinit var binding: ActivityQuestionBinding
    private lateinit var viewPager: ViewPager2
    var backed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Initiating View Binding
         */
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        /**
         * Initialization of view Pager
         */
        viewPager = binding.pager
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = pagerAdapter

        /**
         * Getting The selected chapter Id
         */
        idChapter = intent.extras?.get("chapter") as Int


        /**
         * Defining Onclick Listeners and Timer
         */
        initListeners();
    }



    private fun initListeners() {
        /**
         * Initialization of Duration Variable with 1 minute as a default value
          */
        var duration =  TimeUnit.MINUTES.toMillis(1)

        /**
         * Lunching The Timer
         */
        val timer = object: CountDownTimer(duration, 1000) {
            override fun onTick(l: Long) {
                var sDuration = String.format(Locale.ENGLISH,"%02d : %02d"
                ,TimeUnit.MILLISECONDS.toMinutes(l)
                ,TimeUnit.MILLISECONDS.toSeconds(l) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)))
                /**
                 * Changing Text Color to red when there is less than 10s left
                 */
                if(sDuration == "00 : 10"){
                    binding.timer.setTextColor(Color.RED)
                }
                binding.timer.text = sDuration
            }

            /**
             * When The Timer gets to 0 we lunch the result Activity
             */
            override fun onFinish() {
                lunchResultActivity()
            }
        }.start()

        /**
         * Function to execute when the user clicks next
         */
        binding.btnNext.setOnClickListener {
            if (viewPager.currentItem == 9) {
                timer.cancel()
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("questionMap",questionMap)
                startActivity(intent)
                finish()
            } else {
                if(viewPager.currentItem == 8){
                    binding.btnNext.text = "Finish"
                }else{
                    binding.btnNext.text = "Next"
                }

                viewPager.currentItem = viewPager.currentItem + 1
            }
        }

        binding.btnPrevious.setOnClickListener{
            binding.btnNext.text = "Next"
            if (viewPager.currentItem == 0) {
                Toast.makeText(this, "No More", Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, select the previous step.
                viewPager.currentItem = viewPager.currentItem - 1
            }
        }
    }

    /**
     * This Function end the quiz and goes to Results
     */
     fun lunchResultActivity() {
         if(!backed){
             val intent = Intent(this, ResultActivity::class.java)
             intent.putExtra("questionMap",questionMap)
             startActivity(intent)
             finish()
         }
    }


    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            backed = true
            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }
    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUMPAGES
        val contxt: Context = fa.applicationContext
        override fun createFragment(position: Int): Fragment = QuestionPageFragment(position , Supplier(contxt).getQuestions(idChapter))
    }
}