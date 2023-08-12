package com.example.sislibreria.Clases

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Cliente {

    @SerializedName("clienteId")
    @Expose
    var clienteId:Long=0;
    @SerializedName("nombre")
    @Expose
    var nombre:String?=null
    @SerializedName("apellido_pat")
    @Expose
    var apellido_pat:String?=null
    @SerializedName("apellido_mat")
    @Expose
    var apellido_mat:String?=null
    @SerializedName("dni")
    @Expose
    var dni:String?=null
    @SerializedName("telefono")
    @Expose
    var telefono:String?=null
    @SerializedName("direccion")
    @Expose
    var direccion:String?=null
    @SerializedName("correo")
    @Expose
    var correo:String?=null
    @SerializedName("passw")
    @Expose
    var passw:String?=null
    @SerializedName("carnet")
    @Expose
    var carnet:Carnet?=null
    @SerializedName("estado")
    @Expose
    var estado:Boolean=false

    constructor(){}
    constructor(
        clienteId: Long,
        nombre: String?,
        apellido_pat: String?,
        apellido_mat: String?,
        dni: String?,
        telefono: String?,
        direccion: String?,
        correo: String?,
        passw: String?,
        carnet: Carnet?,
        estado: Boolean
    ) {
        this.clienteId = clienteId
        this.nombre = nombre
        this.apellido_pat = apellido_pat
        this.apellido_mat = apellido_mat
        this.dni = dni
        this.telefono = telefono
        this.direccion = direccion
        this.correo = correo
        this.passw = passw
        this.carnet = carnet
        this.estado = estado
    }


}