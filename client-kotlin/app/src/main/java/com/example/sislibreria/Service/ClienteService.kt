package com.example.sislibreria.Service


import com.example.sislibreria.Clases.Cliente
import retrofit2.Call
import retrofit2.http.*

interface ClienteService {

    @GET("cliente")
    fun MostrarCarnet(): Call<List<Cliente>>

    @GET("cliente/custom")
    fun MostrarClientePersonalizado(): Call<List<Cliente>>

    @POST("cliente")
    fun RegistrarCliente(@Body c: Cliente?): Call<Cliente>?

    @PUT("cliente/{id}")
    fun ActualizarCarnet(@Path("id") id:Long, @Body c: Cliente?): Call<List<Cliente>>

    @DELETE("cliente/{id}")
    fun EliminarCarnet(@Path("id") id:Long): Call<List<Cliente>>
}