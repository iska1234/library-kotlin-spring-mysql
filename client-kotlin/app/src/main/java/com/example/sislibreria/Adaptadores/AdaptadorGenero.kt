package com.example.sislibreria.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sislibreria.Clases.Genero
import com.example.sislibreria.R

class AdaptadorGenero(context: Context?, private val listagenero:List<Genero>?): BaseAdapter(){
    private val layoutInflater: LayoutInflater
    init {
        layoutInflater= LayoutInflater.from(context)
    }
    override fun getCount(): Int {
        return listagenero!!.size
    }

    override fun getItem(p0: Int): Any {
        return listagenero!![p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var vista = p1
        if (vista == null) {
            //relacionamos la vista con el layout
            vista = layoutInflater.inflate(R.layout.elemento_listar_genero, p2, false)
            val objGenero = getItem(p0) as Genero
            val lblgeneroId = vista!!.findViewById<TextView>(R.id.lstgeneroId)
            val lbldescgenero = vista!!.findViewById<TextView>(R.id.lstdescgenero)

            //agregamos valores
            lblgeneroId.text = "" + objGenero.generoId
            lbldescgenero.text = "" + objGenero.descripcion
        }
        return vista!!
    }
}