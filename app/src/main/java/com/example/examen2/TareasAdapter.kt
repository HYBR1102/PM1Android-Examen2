package com.example.examen2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.tareas_recyclerview_item.view.*

class TareasAdapter(private val longItemClickistener: (Int) -> Unit) :
    RecyclerView.Adapter<TareasAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvTitulo = v.tvTitulo
        val tvContenido = v.tvContenido
        val tvPrioridad = v.tvPrioridad
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.tareas_recyclerview_item, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount() = Singleton.dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MainActivity::class.java)
            intent.putExtra("titulo", Singleton.dataSet.get(position).titulo)
            intent.putExtra("contenido", Singleton.dataSet.get(position).contenido)
            intent.putExtra("prioridad", Singleton.dataSet.get(position).prioridad)

            holder.itemView.context.startActivity(intent)
        }

        holder.itemView.setOnLongClickListener {
            longItemClickistener.invoke(position)
            true
        }

        holder.tvTitulo.text = Singleton.dataSet.get(position).titulo
        holder.tvContenido.text = Singleton.dataSet.get(position).contenido
        holder.tvPrioridad.text = Singleton.dataSet.get(position).prioridad
    }
}