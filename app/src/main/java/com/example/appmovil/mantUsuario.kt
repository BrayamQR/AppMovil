package com.example.appmovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovil.db.DBHelper
import com.example.appmovil.model.UsuarioModel

class mantUsuario : AppCompatActivity() {

    private lateinit var db: DBHelper
    private lateinit var btnMgNewUser: Button
    private lateinit var recycleView : RecyclerView
    private var ad: UserAdapter? = null
    private var us: UsuarioModel? = null
    private var tipo:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mant_usuario)
        tipo = intent.getStringExtra("tipo")
        initView()
        initRecycleView()
        db = DBHelper(this)
        getUsers()

        btnMgNewUser.setOnClickListener{
            val intent = Intent(this, RegMgUser::class.java)
            intent.putExtra("tipo",tipo)
            startActivity(intent)
        }
        ad?.setOnClickItem {
            Toast.makeText(this, it.nombre,Toast.LENGTH_SHORT).show()
            us = it
        }
        ad?.setOnClickDeleteItem {
            deleteUser(it.id)
        }

    }
    private fun initRecycleView(){
        recycleView.layoutManager = LinearLayoutManager(this)
        ad = UserAdapter()
        recycleView.adapter = ad
    }

    private fun initView(){
        recycleView = findViewById(R.id.listUser)
        btnMgNewUser = findViewById(R.id.btnMgNewUser)
    }

    private fun getUsers(){
        val userList = db.getAllUsers()
        Log.e("pppp","${userList.size}")

        ad?.addItems(userList)
    }

    private fun deleteUser(id:Int){
        if(id == null) return

        val builder = AlertDialog.Builder(this)
        builder.setMessage("¿Seguro que quiere borrar el usuario?")
        builder.setCancelable(true)
        builder.setPositiveButton("Si"){ dialog, _ ->
            db.deleteUser(id)
            getUsers()
            dialog.dismiss()
        }
        builder.setNegativeButton("No"){dialog, _ ->
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
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