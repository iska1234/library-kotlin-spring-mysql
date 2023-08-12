package com.example.sislibreria

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Spinner
import androidx.activity.result.contract.ActivityResultContracts
import com.example.sislibreria.Adaptadores.AdaptadorComboCarnet
import com.example.sislibreria.Clases.Carnet
import com.example.sislibreria.Clases.Cliente
import com.example.sislibreria.Clases.Usuario
import com.example.sislibreria.Remoto.ApiUtil
import com.example.sislibreria.Service.CarnetService
import com.example.sislibreria.Service.ClienteService
import com.example.sislibreria.Service.UsuarioService
import com.example.sislibreria.utilidad.util
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrarClienteActivity : AppCompatActivity() {
    private lateinit var edtNombre: TextInputEditText
    private lateinit var edtApellidom: TextInputEditText
    private lateinit var edtApellidop: TextInputEditText
    private lateinit var edtDni: TextInputEditText
    private lateinit var edtTelefono: TextInputEditText
    private lateinit var edtCorreo: TextInputEditText
    private lateinit var edtPassword: TextInputEditText
    private lateinit var edtDireccion: TextInputEditText
    private lateinit var chkestado: CheckBox
    private lateinit var cbocod: Spinner
    private lateinit var btnRegisrar: Button
    private lateinit var btnRegresar: Button
    var TAG = "DireccionActivity"
    var addressLat=0.0
    var addressLong=0.0

    private var dialogo: android.app.AlertDialog.Builder?=null
    val objcliente= Cliente()
    val objcarnet= Carnet()

//    val objreservaciones = Reservaciones()
    //val objproducto= Producto()

    //
    private var clienteService: ClienteService?=null
    private  var carnetService: CarnetService?=null

    //    private  var reservacionesService: ReservacionService?=null
    //creamos un arraylist de Categoria
    private var registrocliente:List<Cliente>?=null

    private var registrocarnet:List<Carnet>?=null
    //    private var registroreservaciones:List<Reservaciones>?=null
    //creamos un obejto de la clase utilidad
    var objutilidad= util()

    var id=0L
    var nom=""
    var apem=""
    var apep=""
    var dni=""
    var telf=""
    var user=""
    var passw=""
    var dis=""
    var est = false

    var fila=-1
    var indice=-1
    var codcat=0L
    var pos1=-1
    var pos2=-1
    var nomcat=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_cliente)

        Enlazar()
        registrocliente=ArrayList()
        registrocarnet=ArrayList()

        //implementamos el servicio
        carnetService= ApiUtil.carnetsservice
        clienteService= ApiUtil.clientesservice

        Mostrarcarnet()

        edtDireccion.setOnClickListener{
            val i = Intent(this,DireccionActivity::class.java)
            resultLauncher.launch(i)
        }


        btnRegisrar.setOnClickListener {
            nom=edtNombre.getText().toString()
            apem=edtApellidom.getText().toString()
            apep=edtApellidop.getText().toString()
            dni=edtDni.getText().toString()
            dis=edtDireccion.getText().toString()
            telf=edtTelefono.getText().toString()
            user=edtCorreo.getText().toString()
            passw=edtPassword.getText().toString()
            est = if (chkestado.isChecked) {
                true
            } else {
                false
            }
            pos2=cbocod.selectedItemPosition
            codcat= (registrocarnet as ArrayList<Carnet>).get(pos2).carnetId.toLong()
            nomcat= (registrocarnet as ArrayList<Carnet>).get(pos2).codigo.toString()

            objcliente.nombre=nom
                objcliente.apellido_mat=apem
                objcliente.apellido_pat=apep
                objcliente.dni=dni
                objcliente.telefono=telf
                objcliente.direccion=dis
                objcliente.correo=user
                objcliente.passw=passw
            objcliente.estado=est
                objcarnet.carnetId=codcat
                objcliente.carnet=objcarnet

            RegistrarCliente(objcliente)
            DialogoCRUD(this,"Registro de cliente exitoso","Se registro al cliente correctamente")
        }

        btnRegresar.setOnClickListener {
            val regresar= Intent(this, LoginActivity::class.java)
            startActivity(regresar)
        }

    }

    var resultLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
        if(result.resultCode== Activity.RESULT_OK){
            val data =result.data
            val city=data?.getStringExtra("city")
            val address=data?.getStringExtra("address")
            val country=data?.getStringExtra("country")
            addressLat=data?.getDoubleExtra("lat",0.0)!!
            addressLong=data?.getDoubleExtra("lng",0.0)!!

            edtDireccion?.setText("$address $city")

            Log.d(TAG,"City: $city")
            Log.d(TAG,"Address: $address")
            Log.d(TAG,"Country: $country")
            Log.d(TAG,"Lat: $addressLat")
            Log.d(TAG,"Lng: $addressLong")


        }
    }
    private fun Mostrarcarnet() {
        val call= carnetService!!.MostrarCarnetPersonalizados()
        call!!.enqueue(object: Callback<List<Carnet>?> {
            override fun onResponse(
                call: Call<List<Carnet>?>,
                response: Response<List<Carnet>?>
            ) {
                if(response.isSuccessful){
                    registrocarnet=response.body()
                    cbocod.adapter= AdaptadorComboCarnet(this@RegistrarClienteActivity,registrocarnet)
                }
            }
            override fun onFailure(call: Call<List<Carnet>?>, t: Throwable) {
                Log.e("Error: ", t.message!!)
            }
        })
    }
    fun RegistrarCliente(u: Cliente?) {
        val call = clienteService!!.RegistrarCliente(u)
        call!!.enqueue(object : Callback<Cliente?> {
            override fun onResponse(call: Call<Cliente?>, response: Response<Cliente?>) {
                if (response.isSuccessful) {
                    Log.e("mensaje", "Se registro")
                }
            }

            override fun onFailure(call: Call<Cliente?>, t: Throwable) {
                Log.e("Error: ", t.message!!)
            }

        })
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
    private fun Enlazar(){
        edtNombre=findViewById(R.id.edtNombreregistrar)
        edtApellidom=findViewById(R.id.edtapellidom)
        edtApellidop=findViewById(R.id.edtApellidop)
        edtDni=findViewById(R.id.edtDocumento)
        chkestado=findViewById(R.id.chkestadouser)
        edtDireccion=findViewById(R.id.edtDireccion)
        edtTelefono=findViewById(R.id.edtTelefono)
        edtCorreo=findViewById(R.id.edtCorreoRegistrar)
        edtPassword=findViewById(R.id.edtContraseniaregistrar)
        cbocod=findViewById(R.id.cbocodigo)
        btnRegisrar=findViewById(R.id.btnRegistrarCuenta)
        btnRegresar=findViewById(R.id.btnRegresar)
    }

}