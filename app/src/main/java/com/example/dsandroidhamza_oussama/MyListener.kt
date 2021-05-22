package com.example.dsandroidhamza_oussama

import com.example.dsandroidhamza_oussama.models.User

interface MyListener {
    fun registerNewUser(value: User, repass: String)
    fun loginUser(value: User)
    fun uploadDataToDatabase()
}
