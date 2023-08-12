package com.example.sislibreria

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.sislibreria.databinding.ActivityMainBinding
import com.example.sislibreria.utilidad.util

class MainActivity : AppCompatActivity() {

    private val rotateOpen: Animation by lazy{AnimationUtils.loadAnimation(this,R.anim.rotate_open_anim)}
    private val rotateClose: Animation by lazy{AnimationUtils.loadAnimation(this,R.anim.rotate_close_anim)}
    private val fromBottom: Animation by lazy{AnimationUtils.loadAnimation(this,R.anim.from_bottom_anim)}
    private val toBottom: Animation by lazy{AnimationUtils.loadAnimation(this,R.anim.to_bottom_anim)}


    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val objutilidad= util()

    private var clicked=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.contenedor)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            onAddButtonClick()
        }
        binding.fabcarnet.setOnClickListener { view ->
            val fabcarnet=FragmentoCarnet()
            supportFragmentManager.beginTransaction().replace(R.id.contenedor,fabcarnet).commit()
        }
        binding.fabgenero.setOnClickListener { view ->
            val fabgenero=FragmentoGenero()
            supportFragmentManager.beginTransaction().replace(R.id.contenedor,fabgenero).commit()
        }
    }
    private fun onAddButtonClick(){
        setVisibility(clicked)
        setAnimation(clicked)
        clicked=!clicked
    }

    private fun setVisibility(clicked:Boolean){
        if(!clicked){
            binding.fabcarnet.visibility= View.VISIBLE
            binding.fabgenero.visibility= View.VISIBLE
        }else{
            binding.fabcarnet.visibility= View.INVISIBLE
            binding.fabgenero.visibility= View.INVISIBLE
        }
    }

    private fun setAnimation(clicked:Boolean){
        if(!clicked){
            binding.fabgenero.startAnimation(fromBottom)
            binding.fabcarnet.startAnimation(fromBottom)
            binding.fab.startAnimation(rotateOpen)
        }else{
            binding.fabgenero.startAnimation(toBottom)
            binding.fabcarnet.startAnimation(toBottom)
            binding.fab.startAnimation(rotateClose)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //constante para cambiar
        val id=item.itemId
        return when (id) {
            R.id.action_inicio ->{
                val finicio=FragmentoInicio()
                supportFragmentManager.beginTransaction().replace(R.id.contenedor,finicio).commit()
                true
            }
            R.id.action_fraguserrol ->{
                val fusuariorol=FragmentoUsuarioRol()
                supportFragmentManager.beginTransaction().replace(R.id.contenedor,fusuariorol).commit()
                true
            }
            R.id.action_listadelibros ->{
                val flibros=FragmentoLibros()
                supportFragmentManager.beginTransaction().replace(R.id.contenedor,flibros).commit()
                true
            }
            R.id.action_misclientes ->{
                val fprestamos= FragmentoClientes()
                supportFragmentManager.beginTransaction().replace(R.id.contenedor,fprestamos).commit()
                true
            }
            R.id.action_salir ->{
                objutilidad.SalirSistema(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.contenedor)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}