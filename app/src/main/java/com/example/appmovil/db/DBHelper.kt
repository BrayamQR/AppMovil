package com.example.appmovil.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.appmovil.entidades.UsuarioDto

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

    fun ListarUsuarios(): ArrayList<UsuarioDto>{
        val p0 = this.writableDatabase
        val query = "select * from tblUsuario"
        val ListaUsuario: ArrayList<UsuarioDto> = ArrayList<UsuarioDto>()
        val  cursor: Cursor? = p0.rawQuery(query,null)

        if (cursor != null) {
            if(cursor.moveToFirst()){
                do {
                    var id = cursor.getInt(0)
                    var user = cursor.getString(1)
                    var password = cursor.getString(2)
                    var nombre = cursor.getString(3)
                    var dni = cursor.getString(4)

                    val Usuario = UsuarioDto()
                    Usuario.id = id
                    Usuario.user = user
                    Usuario.password = password
                    Usuario.nombre = nombre
                    Usuario.dni = dni
                    ListaUsuario.add(Usuario)
                }while (cursor.moveToNext())
            }
            cursor.close()
        }
        return ListaUsuario
    }
}