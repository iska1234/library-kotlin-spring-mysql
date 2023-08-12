package com.example.sislibreria.Clases

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.Date


class Libros {
    @SerializedName("libroId")
    @Expose
    var libroId:Long=0

    @SerializedName("titulo")
    @Expose
    var titulo:String?=null

    @SerializedName("autor")
    @Expose
    var autor:String?=null

    @SerializedName("cantidad")
    @Expose
    var cantidad:Int=0

    @SerializedName("editorial")
    @Expose
    var editorial:String?=null

    @SerializedName("estado")
    @Expose
    var estado:Boolean=false

    @SerializedName("genero")
    @Expose
    var genero:Genero?=null


    constructor(){}
    constructor(
        libroId: Long,
        titulo: String?,
        autor: String?,
        cantidad: Int,
        editorial: String?,
        estado: Boolean,
        genero: Genero?,

    ) {
        this.libroId = libroId
        this.titulo = titulo
        this.autor = autor
        this.cantidad = cantidad
        this.editorial = editorial
        this.estado = estado
        this.genero = genero

    }


}