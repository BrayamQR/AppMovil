package com.example.appmovil.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovil.R
import com.example.appmovil.entidades.UsuarioDto

class ListaUsuarioAdapter (
    private val ListaUsuario: ArrayList<UsuarioDto>
    ): RecyclerView.Adapter<ListaUsuarioAdapter.UsuarioViewHolder>() {

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_user, null, false)
        return UsuarioViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ListaUsuario.size;
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = ListaUsuario[position]
        holder.bind(usuario)
    }
    class UsuarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(usuario: UsuarioDto) {
            // Aquí puedes establecer los valores de los elementos de la vista (TextViews, ImageView, etc.) basados en el objeto UsuarioDto
            itemView.findViewById<TextView>(R.id.viewDni).text = usuario.dni
            itemView.findViewById<TextView>(R.id.viewNombre).text = usuario.nombre
        }
    }
}
