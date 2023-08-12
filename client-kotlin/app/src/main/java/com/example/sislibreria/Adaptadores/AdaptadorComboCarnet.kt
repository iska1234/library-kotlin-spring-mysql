package com.example.sislibreria.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.sislibreria.Clases.Carnet
import com.example.sislibreria.R

class AdaptadorComboCarnet (context: Context?, private val listacarnet:List<Carnet>?): BaseAdapter() {
    private val layoutInflater: LayoutInflater
    init {
        layoutInflater= LayoutInflater.from(context)
    }
    override fun getCount(): Int {
        return listacarnet!!.size
    }

    override fun getItem(p0: Int): Any {
        return listacarnet!![p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var vista = p1
        if (vista == null) {
            //relacionamos la vista con el layout
            vista = layoutInflater.inflate(R.layout.elemento_combo_genero, p2, false)
            val objgenero = getItem(p0) as Carnet
            val lbldesc = vista!!.findViewById<TextView>(R.id.lstdescnom)
            //agregamos valores
            lbldesc.text = "" + objgenero.codigo
        }
        return vista!!
    }
}