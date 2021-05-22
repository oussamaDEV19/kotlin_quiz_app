package com.example.dsandroidhamza_oussama.models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
import java.util.ArrayList

data class Quiz(var id:Int = 0, var name: String?, var chapter: Chapter? = null, var questions: ArrayList<Question>? = null){
}