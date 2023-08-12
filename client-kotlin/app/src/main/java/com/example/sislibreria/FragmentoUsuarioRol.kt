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
import com.example.sislibreria.Adaptadores.AdaptadorComboRol
import com.example.sislibreria.Adaptadores.AdaptadorComboUsuario
import com.example.sislibreria.Adaptadores.AdaptadorUsuarioRol
import com.example.sislibreria.Clases.*
import com.example.sislibreria.Remoto.ApiUtil
import com.example.sislibreria.Service.GeneroService
import com.example.sislibreria.Service.RolService
import com.example.sislibreria.Service.UsuarioRolService
import com.example.sislibreria.Service.UsuarioService
import com.example.sislibreria.databinding.FragmentFragmentoGeneroBinding
import com.example.sislibreria.databinding.FragmentUsuarioRolBinding
import com.example.sislibreria.utilidad.util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentoUsuarioRol : Fragment() {

    private lateinit var txtId: TextView
    private lateinit var chkestado: CheckBox
    private lateinit var cbousuario:Spinner
    private lateinit var cborol:Spinner
    private lateinit var lstUsuariorol: ListView
    private lateinit var btnAgregar: Button
    private lateinit var btnEditar: Button
    private lateinit var btnEliminar: Button


    private val binding get() = _binding!!
    private var _binding: FragmentUsuarioRolBinding?=null
    var ft: FragmentTransaction?=null


    private val objusuario= Usuario()
    private val objrol= Rol()
    private val objusuariorol=UsuarioRol()

    private var cod=0L
    private var fila=-1
    private var codusu=0L
    var pos1=-1
    var nomusu=""
    var pos2=-1
    var codrol=0L
    var usu=""
    var nomrol=""
    var est=false
    var indice=-1
    var indice2=-1



    private var id=0L

    private var dialogo: AlertDialog.Builder?=null
    //llamamos al servicio
    private var usuarioService: UsuarioService?=null
    private var usuariorolService: UsuarioRolService?=null
    private var rolService: RolService?=null

    private var registrousuariorol:List<UsuarioRol>?=null
    private var registrousuario:List<Usuario>?=null
    private var registrorol:List<Rol>?=null
    //creamos un objeto de la clase Util
    private val objutilidad= util()
    //creams una variable para actualizar el fragmento



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val raiz=inflater.inflate(R.layout.fragment_usuario_rol,container,false)
        txtId=raiz.findViewById(R.id.tvcoduser)
        chkestado=raiz.findViewById(R.id.chkhabilitadorol)
        cbousuario=raiz.findViewById(R.id.cbuser)
        cborol=raiz.findViewById(R.id.cborol)
        lstUsuariorol=raiz.findViewById(R.id.lstuserol)
        btnAgregar=raiz.findViewById(R.id.btnRegistrarUs)
        btnEditar=raiz.findViewById(R.id.btnActualizarUs)
        btnEliminar=raiz.findViewById(R.id.btnEliminarUs)

        //creamos el registro categoria
        registrorol=ArrayList();
        registrousuario=ArrayList();
        registrousuariorol=ArrayList()

        //implementamos el servicio
        usuarioService= ApiUtil.usuariosservice
        usuariorolService=ApiUtil.usuariorolsservice
        rolService= ApiUtil.rolsservice
        //mostrar el spinner
        MostrarComboUsuario(raiz.context)
        //mostrar los libros
        MostrarComboRol(raiz.context)
        MostrarUsuarioRol(raiz.context)
        btnAgregar.setOnClickListener {
            if (cbousuario.selectedItemPosition == -1) {
                objutilidad.MensajeToast(raiz.context, "Seleccione un usuario")
                cbousuario.requestFocus()
            } else if (cborol.selectedItemPosition == -1) {
                objutilidad.MensajeToast(raiz.context, "Seleccione un rol")
                cborol.requestFocus()
            }
            pos1=cbousuario.selectedItemPosition
            codusu = (registrousuario as ArrayList<Usuario>).get(pos1).userId.toString().toLong()
            nomusu= (registrousuario as ArrayList<Usuario>).get(pos1).nomusu.toString()


            pos2=cborol.selectedItemPosition
            codrol = (registrorol as ArrayList<Rol>).get(pos2).rolId.toString().toLong()
            nomrol= (registrorol as ArrayList<Rol>).get(pos2).nomrol.toString()

            est = if (chkestado.isChecked) {
                true
            } else {
                false
            }

            objusuario.userId=codusu
            objusuariorol.usuario=objusuario

            objrol.rolId=codrol
            objusuariorol.rol=objrol
            objusuariorol.estusurol=est

            RegistrarUsuarioRol(raiz.context,objusuariorol)
            val fusurol = FragmentoUsuarioRol()
            DialogoCRUD("Registro de Usuario-Rol","Se registraron los datos",fusurol)
        }

        lstUsuariorol.setOnItemClickListener(
            AdapterView.OnItemClickListener
            { parent, view, position, id ->
                fila=position
                txtId.setText(""+(registrousuariorol as ArrayList<UsuarioRol>).get(fila).codigo)

                if((registrousuariorol as ArrayList<UsuarioRol>).get(fila).estusurol){
                    chkestado.setChecked(true)
                }else{
                    chkestado.setChecked(false)
                }

                for(i in (registrousuario as ArrayList<Usuario>).indices){
                    if((registrousuario as ArrayList<Usuario>).get(i).nomusu== (registrousuariorol as ArrayList<UsuarioRol>).get(fila).usuario?.nomusu){
                        indice=i
                    }
                }
                cbousuario.setSelection(indice)
                for(i in (registrorol as ArrayList<Rol>).indices){
                    if((registrorol as ArrayList<Rol>).get(i).nomrol== (registrousuariorol as ArrayList<UsuarioRol>).get(fila).rol?.nomrol){
                        indice2=i
                    }
                }
                cborol.setSelection(indice2)

            }
        )

        btnEditar.setOnClickListener {
            if(fila>=0){
                cod=txtId.getText().toString().toLong();
                pos1=cbousuario.selectedItemPosition
                codusu = (registrousuario as ArrayList<Usuario>).get(pos1).userId.toString().toLong()
                nomusu= (registrousuario as ArrayList<Usuario>).get(pos1).nomusu.toString()
                pos2=cborol.selectedItemPosition
                codrol = (registrorol as ArrayList<Rol>).get(pos2).rolId.toString().toLong()
                nomrol= (registrorol as ArrayList<Rol>).get(pos2).nomrol.toString()
                est=if(chkestado.isChecked){
                    true
                }else{
                    false
                }

                objusuariorol.codigo=cod
                objusuario.userId=codusu
                objusuariorol.usuario=objusuario
                objrol.rolId=codrol
                objusuariorol.rol=objrol
                objusuariorol.estusurol=est

                ActualizarUsuarioRol(raiz.context,objusuariorol,cod)
                objutilidad.Limpiar(raiz.findViewById<View>(R.id.frmusuariorol) as ViewGroup)
                val fusurol=FragmentoUsuarioRol()
                DialogoCRUD("Actualizacion de usuario-rol","Se actualizo el libro correctamente",fusurol)

            }else{
                objutilidad.MensajeToast(raiz.context,"Seleccione un elemento de la lista")
                lstUsuariorol.requestFocus()
            }

        }

        btnEliminar.setOnClickListener {
            if(fila>=0){
                cod=txtId.getText().toString().toLong();
                objusuariorol.codigo=cod
                EliminarLibros(raiz.context,cod)
                objutilidad.Limpiar(raiz.findViewById<View>(R.id.frmusuariorol) as ViewGroup)
                val fLibros=FragmentoUsuarioRol()
                DialogoCRUD("Eliminacion de usuario-rol","Se elimino el usuario-rol",fLibros)
            }
        }


        return raiz
    }

    fun RegistrarUsuarioRol(context: Context?, c: UsuarioRol?){
        val call= usuariorolService!!.RegistrarUsuarioRol(c)
        call.enqueue(object: Callback<List<UsuarioRol>> {
            override fun onResponse(
                call: Call<List<UsuarioRol>>,
                response: Response<List<UsuarioRol>>
            ) {
                if(response.isSuccessful){
                    objutilidad.MensajeToast(context,"Se registro el genero")
                }
            }
            override fun onFailure(call: Call<List<UsuarioRol>>, t: Throwable) {
                objutilidad.MensajeToast(context,"erorr"+t.message)
            }
        })
    }

    fun ActualizarUsuarioRol(context: Context?,g: UsuarioRol?,id:Long){
        val call=usuariorolService!!.ActualizarUsuarioRol(id,g)
        call.enqueue(object : Callback<List<UsuarioRol>> {
            override fun onResponse(
                call: Call<List<UsuarioRol>>,
                response: Response<List<UsuarioRol>>
            ){
                if(response.isSuccessful){
                    Log.e("mensaje","Se actualizó")
                }
            }
            override fun onFailure(call: Call<List<UsuarioRol>>, t: Throwable) {
                Log.e("Error",t.message!!)
            }
        })
    }

    fun EliminarLibros(context: Context?,id:Long){
        val call=usuariorolService!!.EliminarUsuarioRol(id)
        call.enqueue(object : Callback<List<UsuarioRol>> {
            override fun onResponse(
                call: Call<List<UsuarioRol>>,
                response: Response<List<UsuarioRol>>
            ){
                if(response.isSuccessful){
                    Log.e("mensaje","Se eliminó")
                }
            }
            override fun onFailure(call: Call<List<UsuarioRol>>, t: Throwable) {
                Log.e("Error",t.message!!)
            }
        })
    }

    fun MostrarUsuarioRol(context: Context?){
        val call=usuariorolService!!.MostrarUsuarioRolPersonalizado()
        call.enqueue(object : Callback<List<UsuarioRol>>{
            override fun onResponse(
                call: Call<List<UsuarioRol>>,
                response: Response<List<UsuarioRol>>
            ){
                if(response.isSuccessful){
                    registrousuariorol=response.body()
                    lstUsuariorol.adapter=AdaptadorUsuarioRol(context,registrousuariorol)
                }
            }
            override fun onFailure(call: Call<List<UsuarioRol>>, t: Throwable) {
                objutilidad.MensajeToast(context,"erorr"+t.message)
            }
        })
    }
    fun MostrarComboRol(context: Context?){
        val call=rolService!!.MostrarRolPersonalizado()
        call.enqueue(object : Callback<List<Rol>> {
            override fun onResponse(
                call: Call<List<Rol>>,
                response: Response<List<Rol>>
            ){
                if(response.isSuccessful){
                    registrorol=response.body()
                    cborol.adapter= AdaptadorComboRol(context,registrorol)
                }
            }
            override fun onFailure(call: Call<List<Rol>>, t: Throwable) {
                objutilidad.MensajeToast(context,"Erorr"+t.message)
            }
        })
    }
    fun MostrarComboUsuario(context: Context?){
        val call=usuarioService!!.MostrarUsuarioPersonalizado()
        call.enqueue(object : Callback<List<Usuario>> {
            override fun onResponse(
                call: Call<List<Usuario>>,
                response: Response<List<Usuario>>
            ){
                if(response.isSuccessful){
                    registrousuario=response.body()
                    cbousuario.adapter= AdaptadorComboUsuario(context,registrousuario)
                }
            }
            override fun onFailure(call: Call<List<Usuario>>, t: Throwable) {
                objutilidad.MensajeToast(context,"Erorr"+t.message)
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