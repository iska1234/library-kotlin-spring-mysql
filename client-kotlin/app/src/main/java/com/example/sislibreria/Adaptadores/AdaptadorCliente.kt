package com.example.sislibreria.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.sislibreria.Clases.Cliente
import com.example.sislibreria.R

class AdaptadorCliente (context: Context?, private val listacliente:List<Cliente>?): BaseAdapter() {
    private val layoutInflater: LayoutInflater
    init {
        layoutInflater= LayoutInflater.from(context)
    }
    override fun getCount(): Int {
        return listacliente!!.size
    }

    override fun getItem(p0: Int): Any {
        return listacliente!![p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var vista = p1
        if (vista == null) {
            //relacionamos la vista con el layout
            vista = layoutInflater.inflate(R.layout.elemento_cliente, p2, false)
            val objcarnet = getItem(p0) as Cliente
            val lblcarnetid = vista!!.findViewById<TextView>(R.id.lblapecli)
            val lblcarnetcod = vista!!.findViewById<TextView>(R.id.lblcorreo)
            val lbltcarnet = vista!!.findViewById<TextView>(R.id.lblcarnet)
            val lblnprestamos = vista!!.findViewById<TextView>(R.id.lblestado)
            //agregamos valores
            lblcarnetid.text = "" + objcarnet.apellido_pat
            lblcarnetcod.text = "" + objcarnet.correo
            lbltcarnet.text = "" + objcarnet.carnet!!.codigo
            lblnprestamos.text = "" + objcarnet.estado

        }
        return vista!!
    }
}