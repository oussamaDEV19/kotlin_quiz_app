package com.example.dsandroidhamza_oussama

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.dsandroidhamza_oussama.databinding.ActivityMainBinding
import com.example.dsandroidhamza_oussama.models.User
import java.io.*
import java.util.*

class MainActivity : AppCompatActivity(), MyListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewPager: ViewPager = binding.viewPager
        val pagerAdapter = AuthenticationPagerAdapter(supportFragmentManager)
        pagerAdapter.addFragment(LoginFragment())
        pagerAdapter.addFragment(RegisterFragment())
        viewPager.adapter = pagerAdapter
    }

    internal inner class AuthenticationPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(
            fm!!,
            BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
        private val fragmentList: ArrayList<Fragment> = ArrayList<Fragment>()
        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getItem(i: Int): Fragment {
            return fragmentList[i]
        }


        fun addFragment(fragment: Fragment) {
            fragmentList.add(fragment)
        }
    }

    override fun registerNewUser(value: User, repass: String) {
        uploadDataToDatabase()
        if (value.password.equals(repass)) {
            DataBaseHandler(this).insertUser(value)
            userName = value.name.toString()
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("userMail", value.email)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Passwords Do Not Match Each other", Toast.LENGTH_LONG).show()
        }
    }

    override fun loginUser(value: User) {
        lateinit var mediaPlayer: MediaPlayer
        mediaPlayer = MediaPlayer.create(this , R.raw.open_app)
        if (DataBaseHandler(this).verifyLogin(value)) {
            val intent = Intent(this, HomeActivity::class.java)
            mediaPlayer.start()
            intent.putExtra("userMail", value.email)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Incorrect Login Or Password", Toast.LENGTH_SHORT).show()
        }
    }

    override fun uploadDataToDatabase() {
        if (!databaseExist()) {
            val dataHandler = DataBaseHandler(this)
            for (x in readFromFile(R.raw.answers)) {
                dataHandler.insertAnswerLine(x)
            }
            for (x in readFromFile(R.raw.questions)) {
                dataHandler.insertQuestionLine(x)
            }
            for (x in readFromFile(R.raw.quizes)) {
                dataHandler.insertQuizLine(x)
            }
            for (x in readFromFile(R.raw.chapters)) {
                dataHandler.insertChapterLine(x)
            }
            //Toast.makeText(this, "successfully registered", Toast.LENGTH_SHORT).show()
        }

    }


    @SuppressLint("SdCardPath")
    private fun databaseExist(): Boolean {
        val dbFile = File("/data/data/com.example.dsandroidhamza_oussama/databases/dbQCM")
        return dbFile.exists()
    }


    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is LoginFragment) {
            fragment.setOnButtonClickedListener(this)
        } else if (fragment is RegisterFragment) {
            fragment.setOnButtonClickedListener(this)
        }
    }


    private fun readFromFile(filePath: Int): ArrayList<String> {
        val myList = ArrayList<String>()
        var string: String? = ""
        val input: InputStream = this.resources.openRawResource(filePath)
        val reader = BufferedReader(InputStreamReader(input))
        while (true) {
            try {
                if (reader.readLine().also { string = it } == null) break
            } catch (e: IOException) {
                e.printStackTrace()
            }
            myList.add(string!!)
        }
        input.close()
        return myList
    }
}