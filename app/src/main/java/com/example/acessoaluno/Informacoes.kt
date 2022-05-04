package com.example.acessoaluno

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.acessoaluno.database.MakeActions
import java.util.*
import kotlin.collections.ArrayList

class Informacoes : AppCompatActivity() {
    private lateinit var aulaRecycler:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacoes)
        aulaRecycler = findViewById(R.id.recycler)
        val rgm = intent.getStringExtra("rgm")
        //LocationServices
        val current = Date().toString().split(" ") // 0-dia semana abreviado 1- mes abreviado 2- dia do mes 3-HH:MM:mm 4-GMT 5- Ano
        var day:DIA? = null
        for(actual in DIA.values()){
            if(actual.en_abrev == current[0]){
                day = actual
            }
        }
        val vals = MakeActions(baseContext).classFromUser(rgm!!)
        var aulas = ArrayList<Aula>()
        while(vals.moveToNext()){
            val indexweekDay = vals.getColumnIndex("dia")
            val indexMateria = vals.getColumnIndex("materia")
            val indexProfessor = vals.getColumnIndex("professor")
            val indexInicio = vals.getColumnIndex("inicio")
            val indexFim = vals.getColumnIndex("fim")
            val indexAula = vals.getColumnIndex("ID")

            val inicio = vals.getInt(indexInicio)
            val fim = vals.getInt(indexFim)
            val professor = vals.getString(indexProfessor)
            val materia = vals.getString(indexMateria)
            val aulaId = vals.getInt(indexAula)
            //val rgm = vals.getString(indexRgm)
            val WeekOfDay:DIA =DIA.values()[vals.getInt(indexweekDay)]
            if(WeekOfDay == day){
                val aula = Aula(professor,inicio,fim,materia,aulaId)
                aulas.add(aula)
                generateClass(professor,materia,inicio,fim)
            }
        }
            //val layout = LinearLayoutManager(baseContext)
            //layout.orientation = LinearLayoutManager.VERTICAL
            //aulaRecycler.layoutManager = layout
            aulaRecycler.adapter = AulaRecycler(aulas.toTypedArray(),rgm)

    }
    private fun generateClass(professor:String,ClassName:String,start:Int,end:Int){

    }
    private fun generateMessage(message:String = ""){

    }
    fun btnBack(view: View){
        intent = Intent(this,MainActivity::class.java)
        finish()
        startActivity(intent)
    }
}