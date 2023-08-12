package com.example.sislibreria.Clases

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UsuarioRol {

    @SerializedName("codigo")
    @Expose
    var codigo:Long=0

    @SerializedName("usuario")
    @Expose
    var usuario:Usuario?=null

    @SerializedName("rol")
    @Expose
    var rol:Rol?=null

    @SerializedName("estusurol")
    @Expose
    var estusurol:Boolean=false

    constructor(){}
    constructor(codigo: Long, usuario: Usuario?, rol: Rol?, estusurol: Boolean) {
        this.codigo = codigo
        this.usuario = usuario
        this.rol = rol
        this.estusurol = estusurol
    }
}