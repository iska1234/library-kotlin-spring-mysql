package com.example.sislibreria.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.sislibreria.Clases.Libros
import com.example.sislibreria.R

//herencia
class AdaptadorLibros(context: Context?, private val listalibros:List<Libros>?): BaseAdapter() {

    private val layoutInflater:LayoutInflater
    init {
        layoutInflater=LayoutInflater.from(context)
    }
    override fun getCount(): Int {
        return listalibros!!.size
    }

    override fun getItem(p0: Int): Any {
        return listalibros!![p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var vista=p1
        if(vista==null){
            //relacionamos la vista con el layout
            vista=layoutInflater.inflate(R.layout.elemento_listar_libro,p2,false)
            val objlibro=getItem(p0) as Libros
            //creamos los controles
            val lblCodLibro=vista!!.findViewById<TextView>(R.id.lstlibroId)
            val lblTitutloLibro=vista!!.findViewById<TextView>(R.id.lsttitulolibro)
            val lblEstadoLibro=vista!!.findViewById<TextView>(R.id.lstestadolibro)
            val lblFPubLibro=vista!!.findViewById<TextView>(R.id.lstfpublicacionlibro)
            val lblGeneroLibro=vista!!.findViewById<TextView>(R.id.lstgenerolibro)

            //agregamos valores
            lblCodLibro.text=""+objlibro.libroId
            lblTitutloLibro.text=""+objlibro.titulo
            lblFPubLibro.text=""+objlibro.autor
            lblGeneroLibro.text=""+objlibro.genero!!.descripcion
            if(objlibro.estado==true){
                lblEstadoLibro.text="Habilitado"
            }else{
                lblEstadoLibro.text="Deshabilitado"
            }
        }
        return vista!!
    }
}