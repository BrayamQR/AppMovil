package com.example.appmovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

class Home : AppCompatActivity() {

    private lateinit var btnProduct1 : Button
    private lateinit var btnProduct2 : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        btnProduct1 = findViewById(R.id.btnProduct1)
        btnProduct2 = findViewById(R.id.btnProduct2)

        btnProduct1.setOnClickListener{
            val intent = Intent(this, product1::class.java)
            startActivity(intent)
        }
        btnProduct2.setOnClickListener{
            val intent = Intent(this, product2::class.java)
            startActivity(intent)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btnInicio->{
                startActivity(Intent(this,Home::class.java))
                return true
            }
            R.id.btnCerrar->{
                startActivity(Intent(this,MainActivity::class.java))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}