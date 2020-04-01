package com.example.examen2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTitulo.text = intent.getStringExtra("titulo")
        tvContenido.text = intent.getStringExtra("contenido")
        tvPrioridad.text = intent.getStringExtra("prioridad")
        ivTarea.setImageResource(R.drawable.tarea)
    }
}
