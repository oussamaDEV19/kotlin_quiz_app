package com.example.dsandroidhamza_oussama.models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class UserQuizScore(var id:Int,var user:User? , var quiz: Quiz?,var score:Int) {
}