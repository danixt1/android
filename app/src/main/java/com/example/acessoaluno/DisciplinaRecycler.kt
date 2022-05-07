package com.example.acessoaluno

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DisciplinaRecycler(private val aulas:ArrayList<String>):RecyclerView.Adapter<DisciplinaRecycler.ViewHolder>() {
    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        lateinit var text:TextView
        init{
            text = view.findViewById(R.id.item_disciplina_text)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.disciplina_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var textToShow = aulas[position]
        holder.text.text = textToShow
    }

    override fun getItemCount(): Int {
        return aulas.count()
    }
}