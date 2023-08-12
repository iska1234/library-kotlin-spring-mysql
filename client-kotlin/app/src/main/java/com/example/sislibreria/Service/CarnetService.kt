package com.example.sislibreria.Service

import com.example.sislibreria.Clases.Carnet

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CarnetService {

    @GET("carnet")
    fun MostrarCarnet(): Call<List<Carnet>>

    @GET("carnet/custom")
    fun MostrarCarnetPersonalizados(): Call<List<Carnet>>

    @POST("carnet")
    fun RegistrarCarnet(@Body c: Carnet?): Call<List<Carnet>>

    @PUT("carnet/{id}")
    fun ActualizarCarnet(@Path("id") id:Long, @Body c: Carnet?): Call<List<Carnet>>

    @DELETE("carnet/{id}")
    fun EliminarCarnet(@Path("id") id:Long): Call<List<Carnet>>
}