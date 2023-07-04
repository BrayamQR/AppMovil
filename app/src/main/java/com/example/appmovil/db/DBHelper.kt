package com.example.appmovil.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context):SQLiteOpenHelper(context, "DB_AppMovil",null,1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table tblUsuario (id integer primary key autoincrement, user text, password text, nombre text, dni text)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("drop table if exists tblUsuario")
    }

    fun InsertUser(user: String, password: String, nombre: String, dni: String): Boolean {
        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("user",user)
        cv.put("password",password)
        cv.put("nombre",nombre)
        cv.put("dni",dni)
        val result = p0.insert("tblUsuario",null,cv)
        if(result== -1.toLong()){
            return false
        }
        return true
    }

    fun CheckExistUser(user: String, password: String): Boolean {
        val p0 = this.writableDatabase
        val query = "select * from tblUsuario where user = '$user' and password = '$password'"
        val cursor = p0.rawQuery(query, null)
        if(cursor.count<= 0){
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }


}