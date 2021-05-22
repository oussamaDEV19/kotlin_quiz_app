package com.example.dsandroidhamza_oussama.models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Question(var id:Int, var question: String?,public  var quiz: Int? = null,public var answers: ArrayList<Answer>? = null):Serializable {
}