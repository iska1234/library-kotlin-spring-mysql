package com.example.sislibreria.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.sislibreria.Clases.Carnet
import com.example.sislibreria.Clases.Cliente
import com.example.sislibreria.R

class AdaptadorComboCliente (context: Context?, private val listacliente:List<Cliente>?): BaseAdapter() {
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
            vista = layoutInflater.inflate(R.layout.elemento_combo_cliente, p2, false)
            val objgenero = getItem(p0) as Cliente
            val lbldesc = vista!!.findViewById<TextView>(R.id.lstclientecod)
            //agregamos valores
            lbldesc.text = "" + objgenero.apellido_pat
        }
        return vista!!
    }
}