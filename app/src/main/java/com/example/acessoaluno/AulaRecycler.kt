package com.example.acessoaluno

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.location.Location
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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
    @SuppressLint("MissingPermission")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aula = aulas[position]
        holder.textMateria.text = aula.materia
        holder.textProfessor.text =aula.professor
        holder.textInicio.text = aula.inicio
        holder.textFim.text = aula.fim
        if(Utilitys.getMinutesFromDay() < aula._fim){
            holder.btnRegistrar.setOnClickListener {

                val minutesDay = Utilitys.getMinutesFromDay()

                if(minutesDay >= aula._inicio && aula._fim >= minutesDay){

                    var location = LocationServices.getFusedLocationProviderClient(holder.context)
                    if (Utilitys.isGPSActivated(holder.context)) {
                        location.lastLocation.addOnSuccessListener { loca: Location? ->
                            val unicidLoc = Location("unicid")
                            unicidLoc.latitude = -23.536286105990403
                            unicidLoc.longitude = -46.560337171952156
                            val distance = loca!!.distanceTo(unicidLoc)
                            if(distance > 300){
                                Toast.makeText(holder.context,R.string.recycler_gps_not_in_university,Toast.LENGTH_SHORT).show()
                            }else{
                                val resu = MakeActions(holder.context).RegisterIfNotRegistred(rgm,aula.id)
                                if(!resu){
                                    Toast.makeText(holder.context,R.string.recycler_already_registred,Toast.LENGTH_SHORT).show()
                                }else{
                                    Toast.makeText(holder.context,R.string.recycler_sucess,Toast.LENGTH_SHORT).show()
                                }
                            }
                        }.addOnCanceledListener {
                            Toast.makeText(holder.context,R.string.recycler_gps_fail_location,Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(holder.context,R.string.recycler_gps_authorization,Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(holder.context,R.string.recycler_out_hour,Toast.LENGTH_LONG).show()
                }

            }

        }else{
            holder.btnRegistrar.setBackgroundColor(Color.RED)
        }
    }

    override fun getItemCount() = aulas.size
}