package com.example.dsandroidhamza_oussama

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.dsandroidhamza_oussama.models.*


const val DATABASE_NAME = "dbQCM"

const val COL_ID = "id"
const val TABLE_NAME = "Users"
const val COL_NAME = "name"
const val COL_EMAIL = "email"
const val COL_PASSWORD = "password"


class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        val createTableUsers = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " VARCHAR(256)," +
                COL_EMAIL + " VARCHAR(256)," +
                COL_PASSWORD + " VARCHAR(256))"
        val createTableCourses = "CREATE TABLE courses (id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(256))"
        val createTableChapters = "CREATE TABLE chapters (id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(256), idCourse INTEGER)"
        val createTableQuizes = "CREATE TABLE quizes (id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(256) , idChapter INTEGER)"
        val createTableQuestions = "CREATE TABLE questions (id INTEGER PRIMARY KEY AUTOINCREMENT, question VARCHAR(256) , idQuiz INTEGER)"
        val createTableAnswers = "CREATE TABLE answers (id INTEGER PRIMARY KEY AUTOINCREMENT,answer VARCHAR(256),idQuestion INTEGER,score INTEGER)"
        val createTableUserQuizScore = "CREATE TABLE userQuizScore (id INTEGER PRIMARY KEY AUTOINCREMENT,nameUser VARCHAR(256),nameChapter VARCHAR(256),score INTEGER)"

        db?.execSQL(createTableUsers)
        db?.execSQL(createTableCourses)
        db?.execSQL(createTableChapters)
        db?.execSQL(createTableQuizes)
        db?.execSQL(createTableQuestions)
        db?.execSQL(createTableAnswers)
        db?.execSQL(createTableUserQuizScore)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertUser(user: User) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME, user.name)
        cv.put(COL_EMAIL, user.email)
        cv.put(COL_PASSWORD, user.password)
        var result = db.insert(TABLE_NAME, null, cv)
        if (result == (-1).toLong())
            Toast.makeText(context, "Register Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "successfully registered", Toast.LENGTH_SHORT).show()
    }

    fun getChapterChapterNames(): ArrayList<String> {
        var myList: ArrayList<String> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from userQuizScore"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                myList.add(result.getString(result.getColumnIndex("nameChapter")))
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return myList
    }
    fun getChapterUserNames(): ArrayList<String> {
        var myList: ArrayList<String> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from userQuizScore"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                myList.add(result.getString(result.getColumnIndex("nameUser")))
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return myList
    }
    fun getChapterScores(): ArrayList<Int> {
        var myList: ArrayList<Int> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from userQuizScore"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                myList.add(result.getString(result.getColumnIndex("score")).toInt())
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return myList
    }


    fun getMaxScore(): ArrayList<String> {
        var myList: ArrayList<String> = ArrayList()
        val db = this.readableDatabase

        val query = "Select * from userQuizScore order by score desc limit 3"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                myList.add(result.getString(result.getColumnIndex("nameUser")))
                myList.add(result.getString(result.getColumnIndex("score")))
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return myList
    }



    fun getUsers(): MutableList<User> {
        var list: MutableList<User> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from $TABLE_NAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var user = User(
                        result.getString(result.getColumnIndex(COL_ID)).toInt(),
                        result.getString(result.getColumnIndex(COL_NAME)),
                        result.getString(result.getColumnIndex(COL_EMAIL)),
                        result.getString(result.getColumnIndex(COL_PASSWORD)),
                )
                list.add(user)
            } while (result.moveToNext())
        }

        result.close()
        db.close()

        return list
    }

    fun deleteUser() {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, null, null)
        db.close()
    }


    fun updateUser() {
        val db = this.writableDatabase
        val query = "Select * from $TABLE_NAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var cv = ContentValues()
                cv.put(COL_NAME, "New Name")
                cv.put(COL_EMAIL, 200)


                db.update(
                        TABLE_NAME, cv, COL_ID + "=? AND " + COL_NAME + "=?",
                        arrayOf(
                                result.getString(result.getColumnIndex(COL_ID)),
                                result.getString(result.getColumnIndex(COL_NAME))
                        )
                )
            } while (result.moveToNext())
        }

        result.close()
        db.close()
    }

    fun verifyLogin(value: User): Boolean {
        val users = getUsers()
        for (user in users) {
            if (user.email.equals(value.email) && user.password.equals(value.password)) {
                userName = user.name.toString()
                return true
            }
        }
        return false
    }

    fun getLoggedInUsernameFromEmail(email: String): String? {
        val users = getUsers()
        for (user in users) {
            if (user.email.equals(email)) {
                return user.name
            }
        }
        return email
    }

    fun getQuestions(idChapter: Int): List<Question> {
        val answersList: ArrayList<Answer> = getAllAnswers()
        val questionList: ArrayList<Question> = getQuestionsOfChapter(idChapter)
        for (answer in answersList) {
            for (question in questionList) {
                if (answer.question == question.id) {
                    question.answers?.add(answer)
                }
            }
        }
        return questionList
    }

    private fun getQuestionsOfChapter(idChapter: Int): ArrayList<Question> {
        val allQuestions: ArrayList<Question> = getAllQuestions()
        val questionsOfChapter: ArrayList<Question> = ArrayList<Question>()

        for (question in allQuestions) {
            if (question.quiz == idChapter) {
                questionsOfChapter.add(question)
            }
        }
        return questionsOfChapter
    }

    private fun getAllQuestions(): ArrayList<Question> {
        val questions: ArrayList<Question> = ArrayList<Question>()
        val db = this.readableDatabase
        val query = "Select * from questions"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val question = Question(
                        result.getString(result.getColumnIndex("id")).toInt(),
                        result.getString(result.getColumnIndex("question")),
                        result.getString(result.getColumnIndex("idQuiz")).toInt(),
                        ArrayList<Answer>()
                )
                questions.add(question)
            } while (result.moveToNext())
        }

        result.close()
        db.close()
        return questions
    }

    private fun getAllAnswers(): ArrayList<Answer> {
        val answers: ArrayList<Answer> = ArrayList<Answer>()
        val db = this.readableDatabase
        val query = "Select * from answers "
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val answer = Answer(
                        result.getString(result.getColumnIndex("id")).toInt(),
                        result.getString(result.getColumnIndex("answer")),
                        result.getString(result.getColumnIndex("idQuestion")).toInt(),
                        result.getString(result.getColumnIndex("score")).toInt(),
                )
                answers.add(answer)
            } while (result.moveToNext())
        }

        result.close()
        db.close()
        return answers
    }


    fun insertUserQuizLine(userName: String, chapterName: String, score: Int) {
        val db = this.writableDatabase
        db.execSQL("INSERT INTO userQuizScore(nameUser, nameChapter, score) VALUES ( ?, ?, ?) ", arrayOf(userName, chapterName, score))
    }

    fun insertAnswerLine(x: String) {
        val db = this.writableDatabase
        val values = x.split(",")
        db.execSQL("INSERT INTO answers(answer, idQuestion, score) VALUES ( ?, ?, ?) ", arrayOf(values[0], values[1], values[2]))
    }

    fun insertChapterLine(x: String) {
        val db = this.writableDatabase
        val values = x.split(",")
        db.execSQL("INSERT INTO chapters(name) VALUES (?) ", arrayOf(values[1]))
    }

    fun insertQuizLine(x: String) {
        val db = this.writableDatabase
        val values = x.split(",")
        db.execSQL("INSERT INTO quizes(idChapter) VALUES (?) ", arrayOf(values[1]))
    }

    fun insertQuestionLine(x: String) {
        val db = this.writableDatabase
        val values = x.split(",")
        db.execSQL("INSERT INTO questions(question, idQuiz) VALUES (?, ?) ", arrayOf(values[0], values[1]))
    }


}