package com.example.acessoaluno

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.acessoaluno.database.MakeActions
import com.google.android.gms.location.LocationServices
import java.util.*

class AulaRecycler(private val aulas:Array<Aula>,private val rgm:String):RecyclerView.Adapter<AulaRecycler.ViewHolder>() {
    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val textMateria:TextView
        val textProfessor:TextView
        val textInicio:TextView
        val textFim:TextView
        val btnRegistrar:Button
        val context:Context
        init{
            textMateria = view.findViewById(R.id.materia)
            textProfessor = view.findViewById(R.id.professor)
            textInicio = view.findViewById(R.id.inicio)
            textFim = view.findViewById(R.id.fim)
            btnRegistrar = view.findViewById(R.id.btn_registrar)
            context =view.context
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.aula_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aula = aulas[position]
        holder.textMateria.text = aula.materia
        holder.textProfessor.text =aula.professor
        holder.textInicio.text = aula.inicio
        holder.textFim.text = aula.fim
        holder.btnRegistrar.setOnClickListener {
            val current = Date().toString().split(" ") // 0-dia semana abreviado 1- mes abreviado 2- dia do mes 3-HH:MM:mm 4-GMT 5- Ano
            val time = current[3].split(":")
            val minutesDay =time[0].toInt() * 60 + time[1].toInt()

            if(minutesDay >= aula._inicio && aula._fim >= minutesDay){

                var location = LocationServices.getFusedLocationProviderClient(holder.context)
                if (ActivityCompat.checkSelfPermission(
                        holder.context, Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        holder.context, Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    Toast.makeText(holder.context,"É necessário permitir o aplicativo a acessar a localização",Toast.LENGTH_SHORT).show()
                }else{
                    location.lastLocation.addOnSuccessListener { loca: Location? ->
                        val unicidLoc = Location("unicid")
                        unicidLoc.latitude = -23.536286105990403
                        unicidLoc.longitude = -46.560337171952156
                        val distance = loca!!.distanceTo(unicidLoc)
                        if(distance > 300){
                            Toast.makeText(holder.context,"É Necessário estar na unicid para registrar a presença",Toast.LENGTH_SHORT).show()
                        }else{
                            val resu = MakeActions(holder.context).RegisterIfNotRegistred(rgm,aula.id)
                            if(!resu){
                                Toast.makeText(holder.context,"Você já registrou a presença",Toast.LENGTH_SHORT).show()
                            }
                        }
                    }.addOnCanceledListener {
                        Toast.makeText(holder.context,"Não foi possível obter a localização",Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                    }
                }
            }else{
                Toast.makeText(holder.context,"Não foi possível registrar a presença, fora do horario",Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun getItemCount() = aulas.size
}