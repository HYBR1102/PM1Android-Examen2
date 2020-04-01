package com.example.examen2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val elementos = arrayOf("NINGUNA", "MEDIA", "URGENTE")
        val adaptador = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, elementos)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerPrioridad.adapter = adaptador

        btnAgregar.setOnClickListener {
            val tarea = Tarea("${etTitulo.text}","${etContenido.text}", "${spinnerPrioridad.selectedItem}")
            Singleton.dataSet.add(tarea)

            Snackbar.make(view, "¡Tarea agregada!", Snackbar.LENGTH_LONG).show()
        }
    }
}
