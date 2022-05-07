package com.example.acessoaluno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.acessoaluno.database.MakeActions

class Disciplinas : AppCompatActivity() {
    lateinit var btnBack:Button
    lateinit var viewRecycler: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disciplinas)
        btnBack = findViewById(R.id.btn_disci_back)
        viewRecycler = findViewById(R.id.recycler_disci)
        btnBack.setOnClickListener {
            finish()
        }
        val rgm = intent.getStringExtra("rgm")
        var list = ArrayList<String>()
        var cursor = MakeActions(baseContext).classFromUser(rgm!!)
        while (cursor.moveToNext()){
            val indexMateria = cursor.getColumnIndex("materia")
            val toAdd = cursor.getString(indexMateria)
            if(!list.contains(toAdd))
                list.add(toAdd)
        }
        viewRecycler.adapter = DisciplinaRecycler(list)
    }
}