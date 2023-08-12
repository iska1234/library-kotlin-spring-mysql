package com.example.sislibreria

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.sislibreria.Adaptadores.AdaptadorComboCliente
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

class LoginActivity : AppCompatActivity() {
    private lateinit var tvregistrar : TextView
    private lateinit var btnIngresar : Button
    private lateinit var btnIngresarc : Button
    private lateinit var btnRegistraru: Button
    private lateinit var cbouser: Spinner
    private lateinit var btnsalir: Button
    private lateinit var edtPassw: TextInputEditText
    private lateinit var tilPassw: TextInputLayout
    private lateinit var edtEmail: TextInputEditText
    private lateinit var tilEmail: TextInputLayout

    private val objutilidad=util()
    private var usu=""
    private var cla= ""
    private var dialogo: android.app.AlertDialog.Builder?=null

    val objusuario= Usuario()
    val objcliente= Cliente()
    var id=0L
    var user=""
    var passw=""
    var edtus=""
    var edtpass=""
    var fila=-1
    var indice=-1
    var codcat=0L
    var pos1=-1

    private var clienteService: ClienteService?=null
    private  var usuarioService: UsuarioService?=null

    //    private  var reservacionesService: ReservacionService?=null
    //creamos un arraylist de Categoria
    private var registrocliente:List<Cliente>?=null
    private var registrousuario:List<Usuario>?=null
    val context=this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Enlazar()

        registrocliente = ArrayList()
        registrousuario=ArrayList()
        clienteService = ApiUtil.clientesservice
        usuarioService= ApiUtil.usuariosservice

        MostrarUsuario()

        cbouser.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                pos1 = cbouser.selectedItemPosition
                user = (registrocliente as ArrayList<Cliente>).get(pos1).correo.toString()
                passw = (registrocliente as ArrayList<Cliente>).get(pos1).passw.toString()

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle nothing selected here

            }
        }

        btnIngresar.setOnClickListener {
            edtpass=edtPassw.getText().toString()
            if (edtpass.isEmpty()) {
                tilPassw.setError("Este campo es requerido");
            } else {
                tilPassw.setError(null);
            }
            if(edtpass==passw)
            {
                Toast.makeText(this@LoginActivity, "Bienvenido", Toast.LENGTH_SHORT).show()
                val i = Intent(this,MainActivity::class.java)
                startActivity(i)
            }else{
                Toast.makeText(this@LoginActivity, "No coinciden", Toast.LENGTH_SHORT).show()
            }

        }

        btnIngresarc.setOnClickListener{
            if(edtEmail.getText().toString()==""){
                objutilidad.MensajeToast(this,"Ingrese el usuario")
                edtEmail.requestFocus()
            }else if(edtPassw.getText().toString()==""){
                objutilidad.MensajeToast(this,"Ingrese la clave")
                edtPassw.requestFocus()
            }else{
                //capturamos los valores
                usu=edtEmail.getText().toString()
                cla=edtPassw.getText().toString()

                //enviamos los datos a la clase
                objusuario.nomusu=usu
                objusuario.clausu=cla

                //llamamos al metodo para validar
                ValidarUsuario(context,objusuario)

            }

        }

        tvregistrar.setOnClickListener {
            val formulario= Intent(this, RegistrarClienteActivity::class.java)
            startActivity(formulario)
        }
        btnRegistraru.setOnClickListener{
            val i = Intent(this,RegistrarUsuario::class.java)
            startActivity(i)
        }

        btnsalir.setOnClickListener { finish() }


    }

    private fun MostrarUsuario() {
        val call= clienteService!!.MostrarClientePersonalizado()
        call!!.enqueue(object: Callback<List<Cliente>?> {
            override fun onResponse(
                call: Call<List<Cliente>?>,
                response: Response<List<Cliente>?>
            ) {
                if(response.isSuccessful){
                    registrocliente=response.body()
                    cbouser.adapter= AdaptadorComboCliente(this@LoginActivity,registrocliente)
                }
            }
            override fun onFailure(call: Call<List<Cliente>?>, t: Throwable) {
                Log.e("Error: ", t.message!!)
            }
        })
    }

    fun Enlazar(){
        cbouser=findViewById(R.id.cboapeusuario)
        tilEmail=findViewById(R.id.tilPassEmail)
        edtEmail = findViewById(R.id.edtEmailUsuario)
        tilPassw=findViewById(R.id.tilPassContraseña)
        edtPassw = findViewById(R.id.edtContraseniaUsuario)
        btnIngresar = findViewById(R.id.btnIngresarUsuario)
        btnIngresarc=findViewById(R.id.btnIngresarCliente)
        btnRegistraru=findViewById(R.id.btnRegistrarUsuario)
        tvregistrar = findViewById(R.id.btnRegistrarse)
        btnsalir=findViewById(R.id.btnSalirSistema)
    }

    fun ValidarUsuario(context: Context?, u: Usuario?){
        val call= usuarioService!!.ValidarUsuario(u)
        call!!.enqueue(object : Callback<List<Usuario>?> {
            override fun onResponse(call: Call<List<Usuario>?>, response: Response<List<Usuario>?>) {
                if(response.isSuccessful){
                    Log.e("respuesta",""+ response.body()!!.size)

                    if(response.body()!!.size==0){
                        objutilidad.MensajeToast(context,"Usuario o Clave incorrecta")
                        objutilidad.Limpiar(findViewById<View>(R.id.frmLogin) as ViewGroup)
                        edtEmail.requestFocus()
                    }else{
                        if(response.body()!!.get(0).estusu==false){
                            objutilidad.MensajeToast(context,"Usuario Deshabilitado")
                        }else{
                            objutilidad.MensajeToast(context,"Bienvenidos al Sistema")
                            //creamos una constante para llamar a la actividad que vamos abrir
                            val formulario=Intent(context,MainActivity::class.java)
                            //iniciamos la actividad nueva
                            startActivity(formulario)
                            //cerramos la actividad correspondiente
                            finish()
                            Log.e("mensaje","Se valido")
                        }

                    }

                }
            }

            override fun onFailure(call: Call<List<Usuario>?>, t: Throwable) {

                Log.e("Error: ", t.message!!)
            }


        })
    }
}