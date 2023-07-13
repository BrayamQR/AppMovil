package com.example.appmovil.model

import android.os.Parcel
import android.os.Parcelable

data class UsuarioModel(
    var id: Int = 0,
    var dni: String = "",
    var nombre: String = "",
    var user: String = "",
    var password: String = "",
    var tipo: String = ""
)