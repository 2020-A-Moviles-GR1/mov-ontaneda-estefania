package com.example.gymvirtual.MenusYFragmentos

import android.content.Intent
import android.os.Bundle
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gymvirtual.R
import kotlinx.android.synthetic.main.activity_act_calendario.*


class Act_calendario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_calendario)
        val calendarView = findViewById(R.id.oneCalendar) as CalendarView
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            abrirEjercicios()
        }
    }

    fun abrirEjercicios(){
        val intentExplicito = Intent(
            this,
            Act_ejercicio::class.java
        )
        startActivity(intentExplicito)
    }
}