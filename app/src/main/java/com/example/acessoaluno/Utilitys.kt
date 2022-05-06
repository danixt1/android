package com.example.acessoaluno

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import java.util.*

class Utilitys {
    companion object{
        fun isGPSActivated(context: Context):Boolean{
            if (ActivityCompat.checkSelfPermission(
                    context, Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context, Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ){
                return false
            }
            return true
        }
        fun getMinutesFromDay():Int{
            val current = Date().toString().split(" ") // 0-dia semana abreviado 1- mes abreviado 2- dia do mes 3-HH:MM:mm 4-GMT 5- Ano
            val time = current[3].split(":")
            return time[0].toInt() * 60 + time[1].toInt()
        }
    }
}