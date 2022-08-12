package com.example.navigationdrawerapp

import android.R.attr
import android.R.attr.password
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.text.Editable
import android.widget.Toast
import com.example.navigationdrawerapp.Database.Companion.passWord
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.auth.User



class Database( context: Context) : SQLiteOpenHelper(context, databaseName,null,1){
    companion object{
        val databaseName = "MyDb"
        val tableName = "users"
        val userName = "username"
        val passWord = "password"

    }
    override fun onCreate(db: SQLiteDatabase?) {
       val createTable = ("CREATE TABLE "+ tableName+" (" + userName +  " VARCHAR(256)," + passWord + " VARCHAR(256))" )
        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + tableName)
        onCreate(db)
    }

    fun insertData(user : UserModel) : Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(userName, user.username)
        contentValues.put(passWord, user.password)
        val result = db.insert(tableName, null, contentValues)

        db.close()
        return result
    }

    fun getName(): Cursor? {


        val db = this.readableDatabase


        return db.rawQuery("SELECT * FROM " + tableName, null)


    }




    fun Login(username: Editable, password: Editable) : Boolean{
        val db = this.getWritableDatabase()

        val mCursor : Cursor= db.rawQuery(
            "SELECT * FROM $tableName WHERE username=? AND password=?",
            arrayOf<String>(username.toString(), password.toString())
        )
        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {
                return true
            }
        }
        mCursor.close()
        return false

    }
    fun updateUser(user: UserModel, nuser : UserModel):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(userName, nuser.username)
        contentValues.put(passWord,nuser.password)


        val success = db.update(tableName, contentValues,"username = ?", arrayOf(user.username))

        db.close()
        return success
    }
    //method to delete data
    fun deleteUser(user: UserModel):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(userName, user.username) // EmpModelClass UserId

        val success = db.delete(tableName,"username= ?", arrayOf(user.username))

        db.close()
        return success
    }



}