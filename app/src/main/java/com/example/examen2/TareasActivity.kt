package com.example.examen2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.activity_tareas.*
import kotlinx.android.synthetic.main.fragment_first.*

class TareasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tareas)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            EnviarTarea()
        }
    }

    private fun EnviarTarea() {
        // Create the text message with a string
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Mensaje de prueba")
            type = "text/plain"
        }
        // Verify that the intent will resolve to an activity
        if (sendIntent.resolveActivity(packageManager) != null) {
            startActivity(sendIntent)
        }
    }
}

//val control = intent.extras?.getString("control", "")
//val nombre = intent.extras?.getString("nombre", "")
//val carrera = intent.extras?.getString("carrera","")
//
//
//}
//
//private fun MiMetodo() {
//    Toast.makeText(this, "Se invocó a MiMetodo", Toast.LENGTH_LONG).show()
//}
//
//}
