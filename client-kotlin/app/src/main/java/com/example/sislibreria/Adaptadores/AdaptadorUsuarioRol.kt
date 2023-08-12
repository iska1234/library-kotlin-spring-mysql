package com.example.sislibreria.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.sislibreria.Clases.Libros
import com.example.sislibreria.Clases.UsuarioRol
import com.example.sislibreria.R

class AdaptadorUsuarioRol (context: Context?, private val listausrol:List<UsuarioRol>?): BaseAdapter() {

    private val layoutInflater: LayoutInflater
    init {
        layoutInflater= LayoutInflater.from(context)
    }
    override fun getCount(): Int {
        return listausrol!!.size
    }

    override fun getItem(p0: Int): Any {
        return listausrol!![p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var vista=p1
        if(vista==null){
            //relacionamos la vista con el layout
            vista=layoutInflater.inflate(R.layout.elemento_usuario_rol,p2,false)
            val objlibro=getItem(p0) as UsuarioRol
            //creamos los controles
            val lblcodusrol=vista!!.findViewById<TextView>(R.id.lblcodusurol)
            val lblcodusuario=vista!!.findViewById<TextView>(R.id.lblcodusu)
            val lblcodrol=vista!!.findViewById<TextView>(R.id.lblcodrol)
            val lblEstadoLibro=vista!!.findViewById<TextView>(R.id.lblestusurol)

            //agregamos valores
            lblcodusrol.text=""+objlibro.codigo
            lblcodusuario.text=""+objlibro.usuario!!.nomusu
            lblcodrol.text=""+objlibro.rol!!.nomrol
            if(objlibro.estusurol==true){
                lblEstadoLibro.text="Habilitado"
            }else{
                lblEstadoLibro.text="Deshabilitado"
            }
        }
        return vista!!
    }
}