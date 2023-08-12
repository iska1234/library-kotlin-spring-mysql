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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.sislibreria.Adaptadores.AdaptadorGenero
import com.example.sislibreria.Clases.Carnet
import com.example.sislibreria.Clases.Genero
import com.example.sislibreria.Clases.Libros
import com.example.sislibreria.Remoto.ApiUtil
import com.example.sislibreria.Service.GeneroService
import com.example.sislibreria.databinding.FragmentFragmentoGeneroBinding
import com.example.sislibreria.utilidad.util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentoGenero : Fragment() {

    private lateinit var txtdescripcion: EditText
    private lateinit var tvid: TextView
    private lateinit var lstgenero: ListView
    private lateinit var chkestado: CheckBox
    private lateinit var btnRegistrar: Button
    private lateinit var btnActualizar: Button
    private lateinit var btnEliminar: Button
    private lateinit var btnRegresar: Button

    val objGenero=Genero()
    private var cod=0
    private var fila=-1
    private var desc=""
    private var id=0L
    var est = false

    private var dialogo: AlertDialog.Builder?=null
    //llamamos al servicio

    private var generoservice: GeneroService?=null
    //creamos una lista de tipo categoria
    private var registrogenero:List<Genero>?=null
    //creamos un objeto de la clase Util
    private val objutilidad= util()
    //creams una variable para actualizar el fragmento
    var ft: FragmentTransaction?=null

    private var _binding: FragmentFragmentoGeneroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val raiz=inflater.inflate(R.layout.fragment_fragmento_genero,container,false)
        txtdescripcion=raiz.findViewById(R.id.edtdescripciong)
        tvid=raiz.findViewById(R.id.tvidgen)
        lstgenero=raiz.findViewById(R.id.listgenero)
        chkestado=raiz.findViewById(R.id.chkEstadog)
        btnRegistrar=raiz.findViewById(R.id.btnRegistrarg)
        btnActualizar=raiz.findViewById(R.id.btnActualizarg)
        btnEliminar=raiz.findViewById(R.id.btnEliminarg)
        btnRegresar=raiz.findViewById(R.id.btnRegresarG)

        //creamos el registro categoria
        registrogenero=ArrayList();
        //implementamos el servicio
        generoservice= ApiUtil.generosservice
        //mostrar la lista
        MostrarGenero(raiz.context)
        //creamos el registro categoria

        btnRegistrar.setOnClickListener{
            if(txtdescripcion.getText().toString()=="") {
                objutilidad.MensajeToast(context, "Ingrese la descripcion")
                txtdescripcion.requestFocus()

            }else{
                desc=txtdescripcion.getText().toString()
                est = if (chkestado.isChecked) {
                    true
                } else {
                    false
                }
                objGenero.descripcion=desc
                objGenero.estado=est

                RegistrarGenero(raiz.context,objGenero)

                objutilidad.Limpiar(raiz.findViewById<View>(R.id.frmGenero) as ViewGroup)
                val fgenero=FragmentoGenero()
                DialogoCRUD("Registro de Categoria","Se registro la categoria",fgenero)
            }
        }

        lstgenero.setOnItemClickListener(
            AdapterView.OnItemClickListener
            { parent, view, position, id ->
                fila=position
                tvid.setText(""+(registrogenero as ArrayList<Genero>).get(fila).generoId)
                txtdescripcion.setText(""+(registrogenero as ArrayList<Genero>).get(fila).descripcion)
                if((registrogenero as ArrayList<Genero>).get(fila).estado){
                    chkestado.setChecked(true)
                }else{
                    chkestado.setChecked(false)
                }

            }
        )

        btnActualizar.setOnClickListener {
            if(fila>=0){
                id=tvid.getText().toString().toLong();
                desc=txtdescripcion.getText().toString()
                est=if(chkestado.isChecked){
                    true
                }else{
                    false
                }
                objGenero.generoId=id
                objGenero.descripcion=desc
                objGenero.estado=est

                ActualizarGenero(raiz.context,objGenero,id)
                objutilidad.Limpiar(raiz.findViewById<View>(R.id.frmGenero) as ViewGroup)
                val fgenero=FragmentoGenero()
                DialogoCRUD("Actualizacion de Categoria","Se actualizo la categoria",fgenero)
            }
        }

        btnEliminar.setOnClickListener {
            if(fila>=0){
                id=tvid.getText().toString().toLong();
                objGenero.generoId=id
                EliminarGenero(raiz.context,id)
                objutilidad.Limpiar(raiz.findViewById<View>(R.id.frmGenero) as ViewGroup)
                val fgenero=FragmentoGenero()
                DialogoCRUD("Eliminacion de Categoria","Se elimino la categoria",fgenero)
            }
        }


        return raiz
    }


    fun MostrarGenero(context: Context?){
        val call=generoservice!!.MostrarGeneroPersonalizado()
        call.enqueue(object : Callback<List<Genero>> {
            override fun onResponse(
                call: Call<List<Genero>>,
                response: Response<List<Genero>>
            ){
                if(response.isSuccessful){
                    registrogenero=response.body()
                    lstgenero.adapter= AdaptadorGenero(context,registrogenero)
                }
            }
            override fun onFailure(call: Call<List<Genero>>, t: Throwable) {
                Log.e("Error",t.message!!)
            }
        })
    }


    fun RegistrarGenero(context: Context?, g: Genero?){
        val call= generoservice!!.RegistrarGenero(g)
        call.enqueue(object: Callback<List<Genero>> {
            override fun onResponse(
                call: Call<List<Genero>>,
                response: Response<List<Genero>>
            ) {
                if(response.isSuccessful){
                    Log.e("mensaje","Se registro")
                }
            }
            override fun onFailure(call: Call<List<Genero>>, t: Throwable) {
                Log.e("Error",t.message!!)
            }
        })
    }
    fun ActualizarGenero(context: Context?,g: Genero?,id:Long){
        val call=generoservice!!.ActualizarGenero(id,g)
        call.enqueue(object : Callback<List<Genero>> {
            override fun onResponse(
                call: Call<List<Genero>>,
                response: Response<List<Genero>>
            ){
                if(response.isSuccessful){
                    Log.e("mensaje","Se actualizó")
                }
            }
            override fun onFailure(call: Call<List<Genero>>, t: Throwable) {
                Log.e("Error",t.message!!)
            }
        })
    }

    fun EliminarGenero(context: Context?,id:Long){
        val call=generoservice!!.EliminarGenero(id)
        call.enqueue(object : Callback<List<Genero>> {
            override fun onResponse(
                call: Call<List<Genero>>,
                response: Response<List<Genero>>
            ){
                if(response.isSuccessful){
                    Log.e("mensaje","Se eliminó")
                }
            }
            override fun onFailure(call: Call<List<Genero>>, t: Throwable) {
                Log.e("Error",t.message!!)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun DialogoCRUD(titulo:String,mensaje:String,fragmento:Fragment){
        dialogo=AlertDialog.Builder(context)
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