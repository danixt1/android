package com.example.acessoaluno

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.acessoaluno.database.MakeActions
import com.google.android.gms.location.LocationServices
import java.util.*
import kotlin.collections.ArrayList

class Informacoes : AppCompatActivity() {
    private lateinit var aulaRecycler:RecyclerView
    private lateinit var textLocal:TextView
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacoes)

        textLocal = findViewById(R.id.textlocalizacao)
        aulaRecycler = findViewById(R.id.recycler)

        var location = LocationServices.getFusedLocationProviderClient(baseContext)

        if(Utilitys.isGPSActivated(baseContext)){
            location.lastLocation.addOnSuccessListener { loca: Location? ->
                if(loca != null){
                    val text = "sua latitude:"+ loca.latitude.toString() + " longitude:"+loca.longitude.toString()
                    textLocal.text =text
                }
            }
        }

        val rgm = intent.getStringExtra("rgm")
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
            val WeekOfDay:DIA =DIA.values()[vals.getInt(indexweekDay)]
            if(WeekOfDay == day){
                val aula = Aula(professor,inicio,fim,materia,aulaId)
                aulas.add(aula)
                //generateClass(professor,materia,inicio,fim)
            }
        }
            aulaRecycler.adapter = AulaRecycler(aulas.toTypedArray(),rgm)

    }
    fun btnBack(view: View){
        finish()
    }
}