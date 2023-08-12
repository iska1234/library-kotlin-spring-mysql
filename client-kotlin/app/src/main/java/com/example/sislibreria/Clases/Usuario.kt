package com.example.sislibreria.Clases

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Usuario {

    @SerializedName("userId")
    @Expose
    var userId:Long=0

    @SerializedName("nomusu")
    @Expose
    var nomusu:String?=null

    @SerializedName("clausu")
    @Expose
    var clausu:String?=null

    @SerializedName("estusu")
    @Expose
    var estusu:Boolean=false

    constructor(){}
    constructor(userId: Long, nomusu: String?, clausu: String?, estusu: Boolean) {
        this.userId = userId
        this.nomusu = nomusu
        this.clausu = clausu
        this.estusu = estusu
    }


}