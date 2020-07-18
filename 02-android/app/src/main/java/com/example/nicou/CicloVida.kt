package com.example.nicou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_ciclo_vida.*

class CicloVida : AppCompatActivity() {

    var numeroActual = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciclo_vida)
        Log.i("Activity", "onCreate")
        btn_anadir.setOnClickListener {
            sumarUnValor()
        }
    }

    fun sumarUnValor(){
        tv_numero.text = numeroActual.toString()
        numeroActual = numeroActual + 1
    }

    override fun onStart(){
        super.onStart()
        Log.i("Activity","onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Activity", "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Activity", "onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Activity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Activity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Activity", "onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i("Activity", "onSaveInstanceState")
        outState?.run {
            putInt("numeroActualGuardado", numeroActual)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("Activity", "onRestoreInstanceState")
        val valorRecuperado = savedInstanceState?.getInt("numeroActualGuardado")
        if(valorRecuperado != null){
            this.numeroActual = valorRecuperado
            tv_numero.text = this.numeroActual.toString()
        }
    }

}
