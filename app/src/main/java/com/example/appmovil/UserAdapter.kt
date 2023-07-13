package com.example.appmovil

import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovil.model.UsuarioModel

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){
    private var lstUser: ArrayList<UsuarioModel> = ArrayList()
    private var onClickItem:((UsuarioModel)->Unit)? = null
    private var onClickDeleteItem:((UsuarioModel)->Unit)? = null
    fun addItems(items: ArrayList<UsuarioModel>){
        this.lstUser = items
        notifyDataSetChanged()
    }
    fun setOnClickItem(callback: (UsuarioModel)->Unit){
        this.onClickItem = callback
    }
    fun setOnClickDeleteItem(callback: (UsuarioModel)->Unit){
        this.onClickDeleteItem = callback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_items_users,parent,false)
    )
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = lstUser[position]
        holder.bindView(user)
        holder.itemView.setOnClickListener{onClickItem?.invoke(user)}
        holder.btnlstDelete.setOnClickListener{onClickDeleteItem?.invoke(user)}
    }
    override fun getItemCount(): Int {
        return lstUser.size
    }
    class UserViewHolder(var view: View): RecyclerView.ViewHolder(view){
        private var dni = view.findViewById<TextView>(R.id.lstDni)
        private var name = view.findViewById<TextView>(R.id.lstName)
        private var type = view.findViewById<TextView>(R.id.lstTipo)
        var btnlstDelete = view.findViewById<Button>(R.id.btnlstDelete)
        fun bindView(user: UsuarioModel){
            dni.text = user.dni.toString()
            name.text = user.nombre.toString()
            type.text = user.tipo.toString()
        }
    }
}