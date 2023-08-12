package com.example.sislibreria

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentTransaction
import com.example.sislibreria.Adaptadores.AdaptadorCarnet
import com.example.sislibreria.Clases.Carnet
import com.example.sislibreria.Clases.Libros
import com.example.sislibreria.Remoto.ApiUtil
import com.example.sislibreria.Service.CarnetService
import com.example.sislibreria.databinding.FragmentFragmentoCarnetBinding
import com.example.sislibreria.utilidad.util
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentoCarnet : Fragment() {

    private lateinit var txtcod: EditText
    private lateinit var txtidg: TextView
    private lateinit var txttcarnet: EditText
    private lateinit var txtnprestamos: EditText
    private lateinit var chkestado:CheckBox
    private lateinit var lstcarnet: ListView
    private lateinit var btnRegistrar: Button
    private lateinit var btnActualizar: Button
    private lateinit var btnEliminar: Button
    private lateinit var btnRegresar: Button

    private val objCarnet= Carnet()

    private var cod=0
    private var id=0L
    private var fila=-1
    private var codi=""
    private var tca=""
    private var npr=""
    private var est=false
    //llamamos al servicio
    private var carnetservice: CarnetService?=null
    //creamos una lista de tipo categoria
    private var registrocarnet:List<Carnet>?=null
    //creamos un objeto de la clase Util
    private val objutilidad= util()
    //creams una variable para actualizar el fragmento
    var ft: FragmentTransaction?=null

    private var _binding: FragmentFragmentoCarnetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val raiz=inflater.inflate(R.layout.fragment_fragmento_carnet,container,false)
        txtcod=raiz.findViewById(R.id.edtcodigo)
        txtidg=raiz.findViewById(R.id.tvgeneroId)
        txttcarnet=raiz.findViewById(R.id.edttcarnet)
        txtnprestamos=raiz.findViewById(R.id.edtnprestamos)
        chkestado=raiz.findViewById(R.id.chkestadoc)
        lstcarnet=raiz.findViewById(R.id.listcarnet)
        btnRegistrar=raiz.findViewById(R.id.btnRegistrarc)
        btnActualizar=raiz.findViewById(R.id.btnActualizarc)
        btnEliminar=raiz.findViewById(R.id.btnEliminarc)
        btnRegresar=raiz.findViewById(R.id.btnRegresarC)

        //creamos el registro categoria
        registrocarnet=ArrayList();
        //implementamos el servicio
        carnetservice= ApiUtil.carnetsservice
        //mostrar la lista
        MostrarCarnet(raiz.context)
        //creamos el registro categoria


        btnRegistrar.setOnClickListener{
            if(txtcod.getText().toString()=="") {
                objutilidad.MensajeToast(context, "Ingrese el codigo")
                txtcod.requestFocus()
            }else if(txttcarnet.getText().toString()=="") {
                objutilidad.MensajeToast(context, "Ingrese el tiempo de carnet")
                txttcarnet.requestFocus()
            }else if(txtnprestamos.getText().toString()=="") {
                objutilidad.MensajeToast(context, "Ingrese el numero de prestamos")
                txtnprestamos.requestFocus()

            }else{
                codi=txtcod.getText().toString()
                tca=txttcarnet.getText().toString()
                npr=txtnprestamos.getText().toString()
                est = if (chkestado.isChecked) {
                    true
                } else {
                    false
                }
                objCarnet.codigo=codi
                objCarnet.tcarnet=tca
                objCarnet.estado=est
                objCarnet.nprestamos=npr

                RegistrarCarnet(raiz.context,objCarnet)

                objutilidad.Limpiar(raiz.findViewById<View>(R.id.frmCarnet) as ViewGroup)
                val fcarnet=FragmentoCarnet()
                ft=fragmentManager?.beginTransaction()
                ft?.replace(R.id.contenedor,fcarnet,null)
                ft?.addToBackStack(null)
                ft?.commit()
            }
        }

        btnActualizar.setOnClickListener {
            if(fila>=0){
                id=txtidg.getText().toString().toLong();
                codi=txtcod.getText().toString()
                tca=txttcarnet.getText().toString()
                npr=txtnprestamos.getText().toString()
                est = if (chkestado.isChecked) {
                    true
                } else {
                    false
                }
                objCarnet.carnetId=id
                objCarnet.codigo=codi
                objCarnet.tcarnet=tca
                objCarnet.nprestamos=npr
                objCarnet.estado=est


                ActualizarCarnet(raiz.context,objCarnet,id)

                objutilidad.Limpiar(raiz.findViewById<View>(R.id.frmCarnet) as ViewGroup)
                val fcarnet=FragmentoCarnet()
                ft=fragmentManager?.beginTransaction()
                ft?.replace(R.id.contenedor,fcarnet,null)
                ft?.addToBackStack(null)
                ft?.commit()
            }
        }

        btnEliminar.setOnClickListener {
            if(fila>=0){
                id=txtidg.getText().toString().toLong();
                objCarnet.carnetId=id
                EliminarCarnet(raiz.context,id)
                objutilidad.Limpiar(raiz.findViewById<View>(R.id.frmCarnet) as ViewGroup)
                val fcarnet=FragmentoCarnet()
                ft=fragmentManager?.beginTransaction()
                ft?.replace(R.id.contenedor,fcarnet,null)
                ft?.addToBackStack(null)
                ft?.commit()
            }
        }
        //
        //para la seleccion de la lista
        lstcarnet.setOnItemClickListener(
            AdapterView.OnItemClickListener
        { parent, view, position, id ->
            fila=position
            txtidg.setText(""+(registrocarnet as ArrayList<Carnet>).get(fila).carnetId)
            txtcod.setText(""+(registrocarnet as ArrayList<Carnet>).get(fila).codigo)
            txttcarnet.setText(""+(registrocarnet as ArrayList<Carnet>).get(fila).tcarnet)
            txtnprestamos.setText(""+(registrocarnet as ArrayList<Carnet>).get(fila).nprestamos)
            if((registrocarnet as ArrayList<Carnet>).get(fila).estado){
                chkestado.setChecked(true)
            }else{
                chkestado.setChecked(false)
            }
        }
        )


        return raiz
    }

    fun MostrarCarnet(context: Context?){
        val call=carnetservice!!.MostrarCarnetPersonalizados()
        call.enqueue(object : Callback<List<Carnet>> {
            override fun onResponse(
                call: Call<List<Carnet>>,
                response: Response<List<Carnet>>
            ){
                if(response.isSuccessful){
                    registrocarnet=response.body()
                    lstcarnet.adapter= AdaptadorCarnet(context,registrocarnet)
                }
            }
            override fun onFailure(call: Call<List<Carnet>>, t: Throwable) {
                objutilidad.MensajeToast(context,"erorr"+t.message)
            }
        })
    }

    fun ActualizarCarnet(context: Context?,c: Carnet?,id:Long){
        val call=carnetservice!!.ActualizarCarnet(id,c)
        call.enqueue(object : Callback<List<Carnet>> {
            override fun onResponse(
                call: Call<List<Carnet>>,
                response: Response<List<Carnet>>
            ){
                if(response.isSuccessful){
                    objutilidad.MensajeToast(context!!,"Se ha actualizado la categoria")
                }
            }
            override fun onFailure(call: Call<List<Carnet>>, t: Throwable) {
                objutilidad.MensajeToast(context,"erorr"+t.message)
            }
        })
    }

    fun EliminarCarnet(context: Context?,id:Long){
        val call=carnetservice!!.EliminarCarnet(id)
        call.enqueue(object : Callback<List<Carnet>> {
            override fun onResponse(
                call: Call<List<Carnet>>,
                response: Response<List<Carnet>>
            ){
                if(response.isSuccessful){
                    objutilidad.MensajeToast(context!!,"Se ha actualizado la categoria")
                }
            }
            override fun onFailure(call: Call<List<Carnet>>, t: Throwable) {
                objutilidad.MensajeToast(context,"erorr"+t.message)
            }
        })
    }

    fun RegistrarCarnet(context: Context?, c:Carnet?){
        val call= carnetservice!!.RegistrarCarnet(c)
        call.enqueue(object: Callback<List<Carnet>>{
            override fun onResponse(
                call: Call<List<Carnet>>,
                response: Response<List<Carnet>>
            ) {
                if(response.isSuccessful){
                    objutilidad.MensajeToast(context,"Se registro el carnet")
                }
            }
            override fun onFailure(call: Call<List<Carnet>>, t: Throwable) {
                objutilidad.MensajeToast(context,"erorr"+t.message)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}