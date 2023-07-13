package com.example.appmovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast

class Home : AppCompatActivity() {

    private lateinit var btnProduct1 : Button
    private lateinit var btnProduct2 : Button
    private var tipo:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        tipo = intent.getStringExtra("tipo")

        btnProduct1 = findViewById(R.id.btnProduct1)
        btnProduct2 = findViewById(R.id.btnProduct2)

        btnProduct1.setOnClickListener{
            val intent = Intent(this, product1::class.java)
            intent.putExtra("tipo",tipo)
            startActivity(intent)
        }
        btnProduct2.setOnClickListener{
            val intent = Intent(this, product2::class.java)
            intent.putExtra("tipo",tipo)
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
                val intent =Intent(this,Home::class.java)
                intent.putExtra("tipo",tipo)
                startActivity(intent)
                return true
            }
            R.id.btnCerrar->{
                startActivity(Intent(this,MainActivity::class.java))
                return true
            }

            R.id.btnMgUser->{
                if(tipo == "Administrador"){
                    val intent =Intent (this,mantUsuario::class.java)
                    intent.putExtra("tipo",tipo)
                    startActivity(intent)
                    return true
                }
                else{
                    Toast.makeText(this, "Acceso denegado", Toast.LENGTH_SHORT).show()
                    return false
                }
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}