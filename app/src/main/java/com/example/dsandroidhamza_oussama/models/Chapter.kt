package com.example.dsandroidhamza_oussama.models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Chapter(var id:Int = 0,
                   var name: String? = "Default Name",var course:Course?=null , var quizs: ArrayList<Quiz>?=null){
}
