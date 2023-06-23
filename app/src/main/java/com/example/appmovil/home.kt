package com.example.appmovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

class home : AppCompatActivity() {

    private lateinit var RedirectUser: Button
    private lateinit var RedirectProduct: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        RedirectUser = findViewById(R.id.redirectUser)
        RedirectProduct = findViewById(R.id.redirectProduct)
        RedirectUser.setOnClickListener{
            val intent = Intent(this, Usuario::class.java)
            startActivity(intent)
        }
        RedirectUser.setOnClickListener{
            val intent = Intent(this, Usuario::class.java)
            startActivity(intent)
        }
    }
}