package com.example.sislibreria.Clases

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Carnet {
    @SerializedName("carnetId")
    @Expose
    var carnetId:Long=0

    @SerializedName("codigo")
    @Expose
    var codigo:String?=null

    @SerializedName("tcarnet")
    @Expose
    var tcarnet:String?=null

    @SerializedName("nprestamos")
    @Expose
    var nprestamos:String?=null

    @SerializedName("estado")
    @Expose
    var estado:Boolean=false

    constructor(){}
    constructor(
        carnetId: Long,
        codigo: String?,
        tcarnet: String?,
        nprestamos: String?,
        estado: Boolean
    ) {
        this.carnetId = carnetId
        this.codigo = codigo
        this.tcarnet = tcarnet
        this.nprestamos = nprestamos
        this.estado = estado
    }


}