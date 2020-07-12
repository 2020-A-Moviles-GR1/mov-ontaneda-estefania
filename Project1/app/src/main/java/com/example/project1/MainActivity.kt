package com.example.project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("Activity", "onCreate")
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
}
