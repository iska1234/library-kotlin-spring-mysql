package com.example.sislibreria.utilidad

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast

class util {
    private var dialogo:AlertDialog.Builder?=null
    fun MensajeToast(context: Context?,men:String){
        Toast.makeText(context,men,Toast.LENGTH_LONG).show()
    }

    //creamos funcion para limpiar
    fun Limpiar(viewGroup: ViewGroup)
    {
        var i = 0
        val count=viewGroup.childCount
        while (i<count){
            val view=viewGroup.getChildAt(i)
            if(view is EditText){
                view.setText("")
            }

            if(view is ViewGroup && view.childCount>0){
                Limpiar(view as ViewGroup)
            }
            i++
        }
    }

    fun SalirSistema(context: Context){

        dialogo=AlertDialog.Builder(context)
        dialogo!!.setTitle("Saliendo del sistema")
        dialogo!!.setMessage("¿Estas seguro que quieres salir del sistema?")
        dialogo!!.setCancelable(false)
        dialogo!!.setPositiveButton("Si"){
            dialogo,which->(context as Activity).finish()
        }
        dialogo!!.setNegativeButton("No"){
            dialogo,which->
        }
        dialogo!!.show()
    }
}