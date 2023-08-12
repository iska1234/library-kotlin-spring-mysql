package com.example.sislibreria.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.sislibreria.Clases.Genero
import com.example.sislibreria.R

class AdaptadorComboGenero (context: Context?, private val listagenero:List<Genero>?): BaseAdapter() {
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
            vista = layoutInflater.inflate(R.layout.elemento_combo_carnet, p2, false)
            val objgenero = getItem(p0) as Genero
            val lbldesc = vista!!.findViewById<TextView>(R.id.lstcarnetcod)
            //agregamos valores
            lbldesc.text = "" + objgenero.descripcion
        }
        return vista!!
    }
}