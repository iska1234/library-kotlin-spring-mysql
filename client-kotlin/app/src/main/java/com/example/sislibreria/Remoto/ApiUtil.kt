package com.example.sislibreria.Remoto

import com.example.sislibreria.Service.*

object ApiUtil {
    //configuramos una constante con la direccion del servicio
    val API_URL="http://192.168.1.40:8095/idat/"

    val carnetsservice:CarnetService?
        get()=RetrofitLibros.getClient(API_URL)?.create(CarnetService::class.java)
    val generosservice:GeneroService?
        get()=RetrofitLibros.getClient(API_URL)?.create(GeneroService::class.java)
    val librosservice:LibrosService?
        get()= RetrofitLibros.getClient(API_URL)?.create(LibrosService::class.java)
    val rolsservice:RolService?
        get()= RetrofitLibros.getClient(API_URL)?.create(RolService::class.java)
    val usuariosservice:UsuarioService?
        get()= RetrofitLibros.getClient(API_URL)?.create(UsuarioService::class.java)
    val usuariorolsservice:UsuarioRolService?
        get()= RetrofitLibros.getClient(API_URL)?.create(UsuarioRolService::class.java)
    val clientesservice:ClienteService?
        get()= RetrofitLibros.getClient(API_URL)?.create(ClienteService::class.java)
}

