package com.example.dsandroidhamza_oussama.models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Answer(var id:Int, var answer: String, var question: Int, var score:Int):Serializable {
}