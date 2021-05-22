package com.example.dsandroidhamza_oussama.models


import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
import java.util.ArrayList

data class Course(var id:Int = 0,
                  var name: String? = "Default Name", var chapters: ArrayList<Chapter>? = null){
}