package com.example.sislibreria.Service


import com.example.sislibreria.Clases.Rol
import retrofit2.Call
import retrofit2.http.GET

interface RolService {

    @GET("rol/custom")
    fun MostrarRolPersonalizado(): Call<List<Rol>>
}