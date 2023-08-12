package com.example.sislibreria.Clases

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Genero {
    @SerializedName("generoId")
    @Expose
    var generoId:Long=0

    @SerializedName("descripcion")
    @Expose
    var descripcion:String?=null

    @SerializedName("estado")
    @Expose
    var estado:Boolean=false


    constructor()
    constructor(generoId: Long, descripcion: String?, estado: Boolean) {
        this.generoId = generoId
        this.descripcion = descripcion
        this.estado = estado
    }


}