package com.example.sislibreria

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import com.example.sislibreria.Clases.Cliente
import com.example.sislibreria.Clases.Usuario
import com.example.sislibreria.Remoto.ApiUtil
import com.example.sislibreria.Service.ClienteService
import com.example.sislibreria.Service.UsuarioService
import com.example.sislibreria.utilidad.util
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrarUsuario : AppCompatActivity() {

    private lateinit var edtPassw: TextInputEditText
    private lateinit var edtEmail: TextInputEditText
    private lateinit var chkestado: CheckBox
    private lateinit var btnregistrar: Button
    private lateinit var btnregresar: Button

    private val objutilidad= util()
    private var usu=""
    private var cla= ""
    var est = false
    private var dialogo: android.app.AlertDialog.Builder?=null

    val objusuario= Usuario()

    private  var usuarioService: UsuarioService?=null

    private var registrousuario:List<Usuario>?=null
    val context=this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_usuario)

        Enlazar()

        registrousuario=ArrayList()
        usuarioService= ApiUtil.usuariosservice


        btnregistrar.setOnClickListener {

            usu= edtEmail.text.toString()
            cla=edtPassw.text.toString()

            est = if (chkestado.isChecked) {
                true
            } else {
                false
            }

            objusuario.nomusu=usu
            objusuario.clausu=cla
            objusuario.estusu=est

            RegistrarUsuario(objusuario)
            DialogoCRUD(this,"Registro de cliente exitoso","Se registro al cliente correctamente")

        }

        btnregresar.setOnClickListener {
            val regresar= Intent(this, LoginActivity::class.java)
            startActivity(regresar)
        }
    }


    fun RegistrarUsuario(u: Usuario?) {
        val call = usuarioService!!.RegistrarUsuario(u)
        call!!.enqueue(object : Callback<Usuario?> {
            override fun onResponse(call: Call<Usuario?>, response: Response<Usuario?>) {
                if (response.isSuccessful) {
                    Log.e("mensaje", "Se registro")
                }
            }
            override fun onFailure(call: Call<Usuario?>, t: Throwable) {
                Log.e("Error: ", t.message!!)
            }
        })
    }
    fun Enlazar(){

        edtEmail = findViewById(R.id.edtusregistrar)
        chkestado = findViewById(R.id.chkestadous)
        edtPassw = findViewById(R.id.edtpassregistrar)
        btnregistrar=findViewById(R.id.btnRegistrarusu)
        btnregresar=findViewById(R.id.btnregresarusu)

    }

    private fun DialogoCRUD(context: Context, titulo:String, mensaje:String){
        dialogo= android.app.AlertDialog.Builder(context)
        dialogo!!.setTitle(titulo)
        dialogo!!.setMessage(mensaje)
        dialogo!!.setCancelable(false)
        dialogo!!.setPositiveButton("Ok"){
                dialog,which->

        }
        dialogo!!.show()
    }

}