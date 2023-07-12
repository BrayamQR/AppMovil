package com.example.appmovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.appmovil.db.DBHelper

class formuser : AppCompatActivity() {
    private lateinit var dni : EditText
    private lateinit var nombre : EditText
    private lateinit var user: EditText
    private lateinit var password : EditText
    private lateinit var confpassword: EditText
    private lateinit var btnGuardar: Button
    private lateinit var db: DBHelper
    private  lateinit var btnCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formuser)

        dni = findViewById(R.id.txtRegDni)
        nombre = findViewById(R.id.txtRegNombre)
        user = findViewById(R.id.txtRegUsuario)
        password = findViewById(R.id.txtRegPassword)
        confpassword = findViewById(R.id.txtRegConfigPassword)
        btnGuardar = findViewById(R.id.btnGuardar)
        btnCancelar = findViewById(R.id.btnCancelar)
        db = DBHelper(this)

        btnGuardar.setOnClickListener{
            val username = user.text.toString()
            val pass = password.text.toString()
            val confpass = confpassword.text.toString()
            val name = nombre.text.toString()
            val doc = dni.text.toString()
            val credencial = "Cliente"
            val savedata = db.InsertUser(username,pass,name,doc,credencial)

            if(TextUtils.isEmpty(username) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(confpass) || TextUtils.isEmpty(name) || TextUtils.isEmpty(doc)){
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            }
            else{
                if(pass.equals(confpass)){
                    if(savedata == true){
                        Toast.makeText(this, "Guardado correctamente", Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                }
            }
        }
        btnCancelar.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



    }
}