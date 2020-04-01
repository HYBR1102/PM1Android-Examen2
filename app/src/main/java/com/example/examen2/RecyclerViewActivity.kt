package com.example.examen2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_recycler_view.*
import kotlin.system.exitProcess

class RecyclerViewActivity : AppCompatActivity(), EliminarTareaDialogFragment.EliminarTareaDialogListener {

    val onLongItemClickListener: (Int) -> Unit = {position ->
        DialogEliminarTarea(position)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        LoadData()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TareasAdapter(onLongItemClickListener)
    }

    override fun onResume() {
        super.onResume()

        recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun LoadData() {
        for(x in 0..20) {
            Singleton.dataSet.add(Tarea("Tarea ${x.toString().padStart(3,'0')}","Contenido ${x}","${if(x%2==0) "NINGUNA" else "MEDIA"}"))
        }
    }

    private fun DialogEliminarTarea(position: Int) {
        val dialog = EliminarTareaDialogFragment(position)
        dialog.show(supportFragmentManager, "NoticeDialogFragment")
    }

    override fun onDialogPositiveClick(position: Int) {
        val tarea = Singleton.dataSet.get(position)
        Singleton.dataSet.removeAt(position)
        recyclerView.adapter?.notifyDataSetChanged()

        Snackbar.make(recyclerView, "Tarea eliminada ${tarea.titulo}", Snackbar.LENGTH_LONG)
            .setAction("Deshacer") {
                Singleton.dataSet.add(position, tarea)
                recyclerView.adapter?.notifyDataSetChanged()
            }.show()
    }

    override fun onDialogNegativeClick(position: Int) {
        Toast.makeText(this, "No se eliminó la tarea", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_tarea, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.agregar_tarea -> {
                startActivity(Intent(this, TareasActivity::class.java))
                true
            }
            R.id.salir -> {
                exitProcess(0);
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}