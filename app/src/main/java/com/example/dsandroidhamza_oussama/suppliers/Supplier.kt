package com.example.dsandroidhamza_oussama.suppliers

import android.content.Context
import com.example.dsandroidhamza_oussama.DataBaseHandler
import com.example.dsandroidhamza_oussama.models.Question

class Supplier(context: Context) {
    var context:Context = context
    fun getQuestions(idChapter: Int): List<Question> {
        return DataBaseHandler(context).getQuestions(idChapter)
    }
}