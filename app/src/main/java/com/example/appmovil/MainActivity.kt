package com.example.appmovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.appmovil.db.DBHelper
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var user: EditText
    private lateinit var pass: EditText
    private  lateinit var btnIngresar: MaterialButton
    private lateinit var btnRegistrar: MaterialButton
    private lateinit var db : DBHelper
    private val defaultUser = "upred"
    private val defaultPassword = "1234"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        user = findViewById(R.id.txtUsuario)
        pass = findViewById(R.id.txtPassword)
        btnIngresar = findViewById(R.id.btnIngresar)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        db = DBHelper(this)
        //db.eliminarTabla()
        btnIngresar.setOnClickListener{
            val username = user.text.toString()
            var password = pass.text.toString()

            if(TextUtils.isEmpty(username)|| TextUtils.isEmpty(password)){
                Toast.makeText(this, "Los campos son obligatorios", Toast.LENGTH_SHORT).show()
            }
            else{
                val checkExistUser = db.ValidateLogin(username,password)
                if(checkExistUser == true){
                    val intent = Intent(this, Home::class.java)
                    val validAdmin = db.ValidateUserIsAdmin(username,password)
                    if(validAdmin == true){
                        intent.putExtra("tipo","Administrador")
                    }
                    else{
                        intent.putExtra("tipo","Cliente")
                    }
                    startActivity(intent)
                    finish()
                }

                else if(username == defaultUser && password == defaultPassword){
                    val intent = Intent(this, Home::class.java)
                    intent.putExtra("tipo","Administrador")
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this, "Crendeciales incorrectas", Toast.LENGTH_SHORT).show()
                }
            }

        }
        btnRegistrar.setOnClickListener{
            val intent = Intent(this, formuser::class.java)
            startActivity(intent)
        }
    }

}