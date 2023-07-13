package com.example.appmovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.appmovil.db.DBHelper
import com.example.appmovil.model.UsuarioModel



class RegMgUser: AppCompatActivity() {

    private lateinit var txtMgDni: EditText
    private lateinit var txtMgNombre: EditText
    private lateinit var txtMgUsuario: EditText
    private lateinit var txtMgPassword: EditText
    private lateinit var txtMgConfigPassword: EditText
    private lateinit var btnMgGuardar: Button
    private lateinit var btnMgVolver: Button
    private lateinit var db: DBHelper
    private lateinit var SelectTipo: AutoCompleteTextView

    private var tipo:String? = null

    var listaTipoUsuario: Array<String> = arrayOf("Administrador", "Cliente")

    private var ad: UserAdapter? = null
    private var us: UsuarioModel? = null
    lateinit var adapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_mg_user)
        tipo = intent.getStringExtra("tipo")
        initView()
        db = DBHelper(this)

        btnMgGuardar.setOnClickListener{
            addUser()
        }
        btnMgVolver.setOnClickListener{
            val intent = Intent(this, mantUsuario::class.java)
            intent.putExtra("tipo",tipo)
            startActivity(intent)
        }
    }

    private fun initView(){
        txtMgDni = findViewById(R.id.txtMgDni)
        txtMgNombre = findViewById(R.id.txtMgNombre)
        txtMgUsuario = findViewById(R.id.txtMgUsuario)
        txtMgPassword = findViewById(R.id.txtMgPassword)
        txtMgConfigPassword = findViewById(R.id.txtMgConfigPassword)
        SelectTipo = findViewById(R.id.selectTipoUsuario)
        adapter = ArrayAdapter<String>(this, R.layout.list_tipousuario,listaTipoUsuario)
        SelectTipo.setAdapter(adapter)
        SelectTipo.setOnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position).toString()
            //Toast.makeText(view.context, "Has hecho clic en: $item", Toast.LENGTH_SHORT).show()
        }

        btnMgGuardar = findViewById(R.id.btnMgGuardar)
        btnMgVolver = findViewById(R.id.btnMgVolver)
    }
    private fun addUser(){
        val username = txtMgUsuario.text.toString()
        val pass = txtMgPassword.text.toString()
        val confpass = txtMgConfigPassword.text.toString()
        val name = txtMgNombre.text.toString()
        val doc = txtMgDni.text.toString()
        val tipo = SelectTipo.text.toString()

        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(confpass) || TextUtils.isEmpty(name) || TextUtils.isEmpty(doc) || TextUtils.isEmpty(tipo)){
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
        }
        else{
            val checkExistUser = db.ValidateUserRegister(username,doc)
            if(checkExistUser == true){
                Toast.makeText(this, "El usuario/Dni ingresado ya existe", Toast.LENGTH_SHORT).show()
            }
            else{
                if(pass.equals(confpass)){
                    val user = UsuarioModel(dni = doc, nombre = name, user = username, password = pass, tipo = tipo)
                    val savedata = db.InsertUser(user)
                    if(savedata == true){
                        Toast.makeText(this, "Guardado correctamente", Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, mantUsuario::class.java)
                        intent.putExtra("tipo",this.tipo)
                        startActivity(intent)
                    }
                    else {
                        Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    /*private fun updateUser(){
        val username = txtMgUsuario.text.toString()
        val pass = txtMgPassword.text.toString()
        val confpass = txtMgConfigPassword.text.toString()
        val name = txtMgNombre.text.toString()
        val doc = txtMgDni.text.toString()
        val tipo = SelectTipo.text.toString()


        if(username == us?.user && pass == us?.password && name == us?.nombre && doc == us?.dni && tipo == us?.tipo && confpass == us?.password){
            Toast.makeText(this, "No se realizó ningun cambio", Toast.LENGTH_SHORT).show()
            return
        }

        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(confpass) || TextUtils.isEmpty(name) || TextUtils.isEmpty(doc) || TextUtils.isEmpty(tipo)){
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            return
        }
        if(!pass.equals(confpass)){
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return
        }
        if(us == null) return
        val user = UsuarioModel(id = us!!.id,dni = doc, nombre = name, user = username, password = pass, tipo = tipo)
        val savedata = db.updateUser(user)
        if(savedata == true){
            Toast.makeText(this, "Modificado correctamente", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "Error al modificar el usuario", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getUserById(id: Int){
        val user = db.getUsersById(id)
        us = user
    }
     */

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