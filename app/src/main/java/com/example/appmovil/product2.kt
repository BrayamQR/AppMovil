package com.example.appmovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class product2 : AppCompatActivity() {
    private var tipo:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product2)
        tipo = intent.getStringExtra("tipo")
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