package com.example.sislibreria

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.FragmentTransaction
import com.example.sislibreria.Adaptadores.AdaptadorCarnet
import com.example.sislibreria.Adaptadores.AdaptadorCliente
import com.example.sislibreria.Adaptadores.AdaptadorComboCliente
import com.example.sislibreria.Clases.Carnet
import com.example.sislibreria.Clases.Cliente
import com.example.sislibreria.Remoto.ApiUtil

import com.example.sislibreria.Service.ClienteService
import com.example.sislibreria.databinding.FragmentoClientesBinding
import com.example.sislibreria.utilidad.util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentoClientes : Fragment() {
    private lateinit var lstcliente: ListView
    private var _binding: FragmentoClientesBinding? = null

    private val binding get() = _binding!!

    val objcliente= Cliente()
    private var clienteService: ClienteService?=null
    private var registrocliente:List<Cliente>?=null
    //creamos un objeto de la clase Util
    private val objutilidad= util()
    //creams una variable para actualizar el fragmento
    var ft: FragmentTransaction?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val raiz=inflater.inflate(R.layout.fragmento_clientes,container,false)
        lstcliente=raiz.findViewById<ListView>(R.id.lstcliente)

        registrocliente = ArrayList()
        clienteService = ApiUtil.clientesservice
        MostrarCliente(raiz.context)
        return raiz
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun MostrarCliente(context: Context?){
        val call=clienteService!!.MostrarClientePersonalizado()
        call.enqueue(object : Callback<List<Cliente>> {
            override fun onResponse(
                call: Call<List<Cliente>>,
                response: Response<List<Cliente>>
            ){
                if(response.isSuccessful){
                    registrocliente=response.body()
                    lstcliente.adapter= AdaptadorCliente(context,registrocliente)
                }
            }
            override fun onFailure(call: Call<List<Cliente>>, t: Throwable) {
                objutilidad.MensajeToast(context,"erorr"+t.message)
            }
        })
    }
}