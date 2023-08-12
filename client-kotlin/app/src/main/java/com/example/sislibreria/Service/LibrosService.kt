package com.example.sislibreria.Service

import com.example.sislibreria.Clases.Libros
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface LibrosService{
    //creamos los metodos para acceder al servicio web
    @GET("libros")
    fun MostrarLibros(): Call<List<Libros>>

    @GET("libros/custom")
    fun MostrarLibrosPersonalizados(): Call<List<Libros>>

    @POST("libros")
    fun RegistrarLibro(@Body c:Libros?):Call<List<Libros>>

    @PUT("libros/{id}")
    fun ActualizarLibro(@Path("id") id:Long,@Body c:Libros?):Call<List<Libros>>

    @DELETE("libros/{id}")
    fun EliminarLibro(@Path("id") id:Long):Call<List<Libros>>

}