package com.example.sislibreria

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentTransaction
import com.example.sislibreria.Adaptadores.AdaptadorComboGenero
import com.example.sislibreria.Adaptadores.AdaptadorLibros
import com.example.sislibreria.Clases.Genero


import com.example.sislibreria.Clases.Libros
import com.example.sislibreria.Remoto.ApiUtil
import com.example.sislibreria.Service.GeneroService
import com.example.sislibreria.Service.LibrosService
import com.example.sislibreria.databinding.FragmentFragmentoLibrosBinding
import com.example.sislibreria.utilidad.util

import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response



class FragmentoLibros : Fragment() {

    private lateinit var txttitulo:EditText
    private lateinit var txtautor:EditText
    private lateinit var txteditorial:EditText
    private lateinit var txtcantidad:EditText
    private lateinit var txtId:TextView
    private lateinit var chkestado:CheckBox
    private lateinit var spinnerGen:Spinner
    private lateinit var lstlibro:ListView
    private lateinit var btnAgregar:Button
    private lateinit var btnEditar:Button
    private lateinit var btnEliminar:Button


    val objgenero=Genero()
    val objlibros=Libros()


    private var cod=0L
    private var pos=-1
    private var fila=-1
    private var tit=""
    private var aut=""
    private var cant=0
    private var edt=""
    private var gen=""
    private var est=false
    var indice=-1

    var codgen=0L
    var nomgen=""

    //llamamos al servicio
    private var generoservice:GeneroService?=null
    private var libroservice:LibrosService?=null
    //creamos una lista de tipo categoria
    private var registrogenero:List<Genero>?=null
    private var registrolibro:List<Libros>?=null
    //creamos un objeto de la clase Util
    private val objutilidad= util()
    //creams una variable para actualizar el fragmento
    var ft:FragmentTransaction?=null

    private var _binding: FragmentoLibros? = null


    private val binding get() = _binding!!

    private var dialogo: AlertDialog.Builder?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val raiz=inflater.inflate(R.layout.fragment_fragmento_libros,container,false)
        txttitulo=raiz.findViewById(R.id.edtTitutlol)
        txtautor=raiz.findViewById(R.id.edtAutorl)
        txteditorial=raiz.findViewById(R.id.edtEditoriall)
        txtcantidad=raiz.findViewById(R.id.edtCantidadl)
        chkestado=raiz.findViewById(R.id.chkEstadol)
        txtId=raiz.findViewById(R.id.tvlibroId)
        spinnerGen=raiz.findViewById(R.id.spinnerGenero)
        lstlibro=raiz.findViewById(R.id.listlibros)
        btnAgregar=raiz.findViewById(R.id.addLibro)
        btnEditar=raiz.findViewById(R.id.editLibro)
        btnEliminar=raiz.findViewById(R.id.deleteLibro)


        //creamos el registro categoria
        registrogenero=ArrayList();
        registrolibro=ArrayList();
        //implementamos el servicio
        generoservice= ApiUtil.generosservice
        libroservice= ApiUtil.librosservice
        //mostrar el spinner
        MostrarComboGenero(raiz.context)
        //mostrar los libros
        MostrarLibro(raiz.context)



        btnAgregar.setOnClickListener {
            if (txttitulo.getText().toString() == "") {
                objutilidad.MensajeToast(raiz.context, "Ingrese el titulo")
                txttitulo.requestFocus()
            } else if (txtautor.getText().toString() == "") {
                objutilidad.MensajeToast(raiz.context, "Ingrese el autor")
                txtautor.requestFocus()
            } else if (txteditorial.getText().toString() == "") {
                objutilidad.MensajeToast(raiz.context, "Ingrese el editorial")
                txteditorial.requestFocus()
            } else if (txtcantidad.getText().toString() == "") {
                objutilidad.MensajeToast(raiz.context, "Ingrese la cantidad")
                txtcantidad.requestFocus()
            } else if (spinnerGen.selectedItemPosition == -1) {
                objutilidad.MensajeToast(raiz.context, "Seleccione un genero")
                spinnerGen.requestFocus()
            } else {
                //capturando valores
                tit = txttitulo.getText().toString()
                aut = txtautor.getText().toString()
                edt = txteditorial.getText().toString()
                cant = txtcantidad.getText().toString().toInt()
                pos = spinnerGen.selectedItemPosition
                cod = (registrogenero as ArrayList<Genero>).get(pos).generoId.toLong()
                gen= (registrogenero as ArrayList<Genero>).get(pos).descripcion.toString()
                est = if (chkestado.isChecked) {
                    true
                } else {
                    false
                }

                //enviamos los valores a la clase
                objlibros.titulo = tit
                objlibros.autor = aut
                objlibros.editorial = edt
                objlibros.cantidad = cant
                objlibros.estado = est
                objgenero.generoId=cod
                objlibros.genero=objgenero


                RegistrarLibro(raiz.context, objlibros)
                val flibros = FragmentoLibros()
                DialogoCRUD("Registro de libros","Se registraron los libros",flibros)
            }
        }
        lstlibro.setOnItemClickListener(
            AdapterView.OnItemClickListener
            { parent, view, position, id ->
                fila=position
                txtId.setText(""+(registrolibro as ArrayList<Libros>).get(fila).libroId)
                txttitulo.setText(""+(registrolibro as ArrayList<Libros>).get(fila).titulo)
                txtautor.setText(""+(registrolibro as ArrayList<Libros>).get(fila).autor)
                txteditorial.setText(""+(registrolibro as ArrayList<Libros>).get(fila).editorial)
                txtcantidad.setText(""+(registrolibro as ArrayList<Libros>).get(fila).cantidad)
                if((registrolibro as ArrayList<Libros>).get(fila).estado){
                    chkestado.setChecked(true)
                }else{
                    chkestado.setChecked(false)
                }
                for(i in (registrogenero as ArrayList<Genero>).indices){
                    if((registrogenero as ArrayList<Genero>).get(i).descripcion== (registrolibro as ArrayList<Libros>).get(fila).genero?.descripcion){
                        indice=i
                    }
                }
                spinnerGen.setSelection(indice)

            }
        )

        btnEditar.setOnClickListener {
            if(fila>=0){
                cod=txtId.getText().toString().toLong();
                tit=txttitulo.getText().toString()
                aut=txtautor.getText().toString()
                edt=txteditorial.getText().toString()
                cant=txtcantidad.getText().toString().toInt()
                pos=spinnerGen.selectedItemPosition
                codgen= (registrogenero as ArrayList<Genero>).get(pos).generoId.toLong()
                nomgen= (registrogenero as ArrayList<Genero>).get(pos).descripcion.toString()
                est=if(chkestado.isChecked){
                    true
                }else{
                    false
                }

                objlibros.libroId=cod
                objlibros.titulo=tit
                objlibros.autor=aut
                objlibros.editorial=edt
                objlibros.cantidad=cant
                objlibros.estado=est

                objgenero.generoId=codgen
                objlibros.genero=objgenero

                ActualizarLibros(raiz.context,objlibros,cod)
                objutilidad.Limpiar(raiz.findViewById<View>(R.id.frmLibros) as ViewGroup)
                val flibros=FragmentoLibros()
                DialogoCRUD("Actualizacion de libros","Se actualizo el libro correctamente",flibros)

            }else{
                objutilidad.MensajeToast(raiz.context,"Seleccione un elemento de la lista")
                lstlibro.requestFocus()
            }
        }

        btnEliminar.setOnClickListener {
            if(fila>=0){
                cod=txtId.getText().toString().toLong();
                objlibros.libroId=cod
                EliminarLibros(raiz.context,cod)
                objutilidad.Limpiar(raiz.findViewById<View>(R.id.frmLibros) as ViewGroup)
                val fLibros=FragmentoLibros()
                DialogoCRUD("Eliminacion de Libros","Se elimino el libro",fLibros)
            }
        }


        return raiz
    }




    fun MostrarComboGenero(context: Context?){
        val call=generoservice!!.MostrarGeneroPersonalizado()
        call.enqueue(object : Callback<List<Genero>> {
            override fun onResponse(
                call: Call<List<Genero>>,
                response: Response<List<Genero>>
            ){
                if(response.isSuccessful){
                    registrogenero=response.body()
                    spinnerGen.adapter= AdaptadorComboGenero(context,registrogenero)
                }
            }
            override fun onFailure(call: Call<List<Genero>>, t: Throwable) {
                objutilidad.MensajeToast(context,"Erorr"+t.message)
            }
        })
    }

    //crear funcion para mostrar
    fun MostrarLibro(context: Context?){
        val call=libroservice!!.MostrarLibrosPersonalizados()
        call.enqueue(object : Callback<List<Libros>>{
            override fun onResponse(
                call: Call<List<Libros>>,
                response: Response<List<Libros>>
            ){
                if(response.isSuccessful){
                    registrolibro=response.body()
                    lstlibro.adapter=AdaptadorLibros(context,registrolibro)
                }
            }
            override fun onFailure(call: Call<List<Libros>>, t: Throwable) {
                objutilidad.MensajeToast(context,"erorr"+t.message)
            }
        })
    }

    fun RegistrarLibro(context: Context?, c: Libros?){
        val call= libroservice!!.RegistrarLibro(c)
        call.enqueue(object: Callback<List<Libros>> {
            override fun onResponse(
                call: Call<List<Libros>>,
                response: Response<List<Libros>>
            ) {
                if(response.isSuccessful){
                    objutilidad.MensajeToast(context,"Se registro el genero")
                }
            }
            override fun onFailure(call: Call<List<Libros>>, t: Throwable) {
                objutilidad.MensajeToast(context,"erorr"+t.message)
            }
        })
    }

    fun ActualizarLibros(context: Context?,g: Libros?,id:Long){
        val call=libroservice!!.ActualizarLibro(id,g)
        call.enqueue(object : Callback<List<Libros>> {
            override fun onResponse(
                call: Call<List<Libros>>,
                response: Response<List<Libros>>
            ){
                if(response.isSuccessful){
                    Log.e("mensaje","Se actualizó")
                }
            }
            override fun onFailure(call: Call<List<Libros>>, t: Throwable) {
                Log.e("Error",t.message!!)
            }
        })
    }

    fun EliminarLibros(context: Context?,id:Long){
        val call=libroservice!!.EliminarLibro(id)
        call.enqueue(object : Callback<List<Libros>> {
            override fun onResponse(
                call: Call<List<Libros>>,
                response: Response<List<Libros>>
            ){
                if(response.isSuccessful){
                    Log.e("mensaje","Se eliminó")
                }
            }
            override fun onFailure(call: Call<List<Libros>>, t: Throwable) {
                Log.e("Error",t.message!!)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun DialogoCRUD(titulo:String,mensaje:String,fragmento:Fragment){
        dialogo= AlertDialog.Builder(context)
        dialogo!!.setTitle(titulo)
        dialogo!!.setMessage(mensaje)
        dialogo!!.setCancelable(false)
        dialogo!!.setPositiveButton("Ok"){
                dialogo,which->
            ft=fragmentManager?.beginTransaction()
            ft?.replace(R.id.contenedor,fragmento,null)
            ft?.addToBackStack(null)
            ft?.commit()
        }
        dialogo!!.show()
    }
}


