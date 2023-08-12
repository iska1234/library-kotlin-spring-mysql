package com.example.sislibreria.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.sislibreria.Clases.Carnet
import com.example.sislibreria.Clases.Libros
import com.example.sislibreria.R


class AdaptadorCarnet(context: Context?, private val listacarnet:List<Carnet>?): BaseAdapter() {
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
            vista = layoutInflater.inflate(R.layout.elemento_listar_carnet, p2, false)
            val objcarnet = getItem(p0) as Carnet
            val lblcarnetid = vista!!.findViewById<TextView>(R.id.lstcarnetId)
            val lblcarnetcod = vista!!.findViewById<TextView>(R.id.lstcarnetcod)
            val lbltcarnet = vista!!.findViewById<TextView>(R.id.lsttcarnet)
            val lblnprestamos = vista!!.findViewById<TextView>(R.id.lstnprestamos)
            //agregamos valores
            lblcarnetid.text = "" + objcarnet.carnetId
            lblcarnetcod.text = "" + objcarnet.codigo
            lbltcarnet.text = "" + objcarnet.tcarnet
            lblnprestamos.text = "" + objcarnet.nprestamos

        }
        return vista!!
    }
}