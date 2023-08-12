package com.example.sislibreria.Service


import com.example.sislibreria.Clases.Cliente
import com.example.sislibreria.Clases.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UsuarioService {

    @GET("usuario/custom")
    fun MostrarUsuarioPersonalizado(): Call<List<Usuario>>

    @POST("usuario")
    fun RegistrarUsuario(@Body c: Usuario?): Call<Usuario>?

    @POST("usuario/login")
    fun ValidarUsuario(@Body u: Usuario?): Call<List<Usuario>?>?
}