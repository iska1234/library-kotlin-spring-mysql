package com.example.sislibreria.Remoto


import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitLibros {

    //creamos la variable para el retrofit
    private var retrofit:Retrofit?=null

    //creamos una funcion para la conexion al retrofit
    fun getClient(url:String?):Retrofit?{
        if (retrofit==null) {
            retrofit=Retrofit.Builder()
                .baseUrl(url).addConverterFactory(GsonConverterFactory.create(
                    GsonBuilder().create()
                )).build()
        }
        return retrofit
    }
}