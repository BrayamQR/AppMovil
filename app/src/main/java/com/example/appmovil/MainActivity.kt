package com.example.appmovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var user: EditText
    private lateinit var pass: EditText
    private  lateinit var btnIngresar: Button

    private val defaultUser = "upredeterminado"
    private val defaultPassword = "123456"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        user = findViewById(R.id.txtUsuario)
        pass = findViewById(R.id.txtPassword)
        btnIngresar = findViewById(R.id.btnIngresar)

        btnIngresar.setOnClickListener{
            val username = user.text.toString()
            var password = pass.text.toString()

            if(username == defaultUser && password == defaultPassword){
                val intent = Intent(this, home::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Crendeciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }
    }
}