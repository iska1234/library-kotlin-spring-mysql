package com.example.sislibreria.Service


import com.example.sislibreria.Clases.Genero
import retrofit2.Call
import retrofit2.http.*

interface GeneroService {

    @GET("genero")
    fun MostrarGenero(): Call<List<Genero>>

    @GET("genero/custom")
    fun MostrarGeneroPersonalizado(): Call<List<Genero>>

    @GET("genero")
    fun buscarpordescripcion(@Query("genero") descripcion: String): Call<List<Genero>>

    @POST("genero")
    fun RegistrarGenero(@Body c: Genero?): Call<List<Genero>>

    @PUT("genero/{id}")
    fun ActualizarGenero(@Path("id") id:Long, @Body g: Genero?): Call<List<Genero>>

    @DELETE("genero/{id}")
    fun EliminarGenero(@Path("id") id:Long): Call<List<Genero>>
}