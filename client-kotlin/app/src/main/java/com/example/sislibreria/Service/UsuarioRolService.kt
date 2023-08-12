package com.example.sislibreria.Service


import com.example.sislibreria.Clases.UsuarioRol
import retrofit2.Call
import retrofit2.http.*

interface UsuarioRolService {

    @GET("usuario-rol")
    fun MostrarGenero(): Call<List<UsuarioRol>>

    @GET("usuario-rol/custom")
    fun MostrarUsuarioRolPersonalizado(): Call<List<UsuarioRol>>

    @POST("usuario-rol")
    fun RegistrarUsuarioRol(@Body c: UsuarioRol?): Call<List<UsuarioRol>>

    @PUT("usuario-rol/{id}")
    fun ActualizarUsuarioRol(@Path("id") id:Long, @Body g: UsuarioRol?): Call<List<UsuarioRol>>

    @DELETE("usuario-rol/{id}")
    fun EliminarUsuarioRol(@Path("id") id:Long): Call<List<UsuarioRol>>
}