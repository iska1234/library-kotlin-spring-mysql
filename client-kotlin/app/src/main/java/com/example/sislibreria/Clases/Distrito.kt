package com.example.sislibreria.Clases

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Distrito {

    @SerializedName("distritoId")
    @Expose
    var distritoId:Long=0
    @SerializedName("nomDistrito")
    @Expose
    var nomDistrito:String?=null

    constructor(){}
    constructor(distritoId: Long, nomDistrito: String?) {
        this.distritoId = distritoId
        this.nomDistrito = nomDistrito
    }
}