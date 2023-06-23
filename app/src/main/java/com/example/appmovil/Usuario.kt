package com.example.appmovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovil.adapter.ListaUsuarioAdapter
import com.example.appmovil.db.DBHelper
import com.example.appmovil.entidades.UsuarioDto

class Usuario : AppCompatActivity() {
    private  lateinit var ListaUsuario: RecyclerView
    private lateinit var btnRedirectFormUser : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)


        btnRedirectFormUser = findViewById(R.id.btnRedirectFormUser)
        ListaUsuario = findViewById(R.id.ListaUsuario)

        ListaUsuario.layoutManager = LinearLayoutManager(this)
        var DBHelper: DBHelper = DBHelper(this)
        var ListarArrayUsuario: ArrayList<UsuarioDto> = ArrayList<UsuarioDto>()
        var adapter : ListaUsuarioAdapter = ListaUsuarioAdapter(DBHelper.ListarUsuarios())
        ListaUsuario.adapter = adapter

        btnRedirectFormUser.setOnClickListener{
            val intent = Intent(this, formuser::class.java)
            startActivity(intent)
        }
    }
}