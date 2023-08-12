package com.example.sislibreria.Clases

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Rol {
    @SerializedName("rolId")
    @Expose
    var rolId:Long=0

    @SerializedName("nomrol")
    @Expose
    var nomrol:String?=null

    @SerializedName("estrol")
    @Expose
    var estrol:Boolean=false

    constructor(){}
    constructor(rolId: Long, nomrol: String?, estrol: Boolean) {
        this.rolId = rolId
        this.nomrol = nomrol
        this.estrol = estrol
    }


}