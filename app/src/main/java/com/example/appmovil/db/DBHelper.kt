package com.example.appmovil.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.appmovil.model.UsuarioModel

class DBHelper(context: Context):SQLiteOpenHelper(context, "DB_AppMovil",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS tblUsuario (id INTEGER PRIMARY KEY AUTOINCREMENT, user TEXT, password TEXT, nombre TEXT, dni TEXT, tipo TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS tblUsuario")
        onCreate(db)
    }

    fun InsertUser(user: UsuarioModel): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("user",user.user)
        cv.put("password",user.password)
        cv.put("nombre",user.nombre)
        cv.put("dni",user.dni)
        cv.put("tipo",user.tipo)
        val result = db.insert("tblUsuario",null,cv)
        if(result== -1.toLong()){
            return false
        }
        return true
    }
    fun eliminarTabla() {
        val db = writableDatabase
        db.execSQL("DROP TABLE IF EXISTS tblUsuario")
        onCreate(db)
    }
    fun ValidateLogin(user: String, password: String): Boolean {
        val db = this.writableDatabase
        val query = "select * from tblUsuario where user = '$user' and password = '$password'"
        val cursor = db.rawQuery(query, null)
        if(cursor.count<= 0){
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }

    fun ValidateUserIsAdmin(user: String, password: String): Boolean{
        val db = this.writableDatabase
        val query = "select * from tblUsuario where user = '$user' and password = '$password' and tipo = 'Administrador'"
        val cursor = db.rawQuery(query, null)
        if(cursor.count<= 0){
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }
    fun ValidateUserRegister(user: String, dni: String): Boolean{
        val db = this.writableDatabase
        val query = "select * from tblUsuario where user = '$user' or dni = '$dni'"
        val cursor = db.rawQuery(query, null)
        if(cursor.count<= 0){
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }

    @SuppressLint("Range")
    fun getAllUsers() : ArrayList<UsuarioModel>{
        val userList: ArrayList<UsuarioModel> = ArrayList()
        val query = "select * from tblUsuario"
        val db = this.readableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery(query, null)
        }
        catch (e: Exception){
            e.printStackTrace()
            db.execSQL(query)
            return ArrayList()
        }
        var id: Int
        var dni: String
        var nombre: String
        var usuario: String
        var pass: String
        var tipo: String

        if(cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                dni = cursor.getString(cursor.getColumnIndex("dni"))
                nombre= cursor.getString(cursor.getColumnIndex("nombre"))
                usuario = cursor.getString(cursor.getColumnIndex("user"))
                pass = cursor.getString(cursor.getColumnIndex("password"))
                tipo = cursor.getString(cursor.getColumnIndex("tipo"))
                val user = UsuarioModel(id = id, dni = dni,nombre = nombre,user = usuario, password = pass, tipo = tipo)
                userList.add(user)
            }while (cursor.moveToNext())
        }
        return userList
    }
    @SuppressLint("Range")
    fun getUsersById(id: Int?) : UsuarioModel?{

        val db = this.writableDatabase
        val query = "select * from tblUsuario where id = $id LIMIT 1"
        val cursor = db.rawQuery(query, null)
        var user : UsuarioModel? = UsuarioModel()
        if(cursor.count<= 0){
            cursor.close()
            return null
        }
        var id: Int
        var dni: String
        var nombre: String
        var usuario: String
        var pass: String
        var tipo: String

        if(cursor.moveToFirst()){
            id = cursor.getInt(cursor.getColumnIndex("id"))
            dni = cursor.getString(cursor.getColumnIndex("dni"))
            nombre= cursor.getString(cursor.getColumnIndex("nombre"))
            usuario = cursor.getString(cursor.getColumnIndex("user"))
            pass = cursor.getString(cursor.getColumnIndex("password"))
            tipo = cursor.getString(cursor.getColumnIndex("tipo"))

            user = UsuarioModel(id = id, dni = dni,nombre = nombre,user = usuario, password = pass, tipo = tipo)
        }
        return user
    }
    fun updateUser(user: UsuarioModel): Boolean{
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("user",user.user)
        cv.put("password",user.password)
        cv.put("nombre",user.nombre)
        cv.put("dni",user.dni)
        cv.put("tipo",user.tipo)

        val result = db.update("tblUsuario",cv,"id="+user.id,null)
        db.close()

        if(result <= -1.toLong()){
            return false
        }
        return true
    }

    fun deleteUser(id: Int): Boolean{
        val db = this.writableDatabase
        val result = db.delete("tblUsuario","id=$id",null)
        db.close()
        if(result <= -1.toLong()){
            return false
        }
        return true
    }
}