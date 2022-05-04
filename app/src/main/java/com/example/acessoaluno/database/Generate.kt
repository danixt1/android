package com.example.acessoaluno.database

import android.content.ContentValues
import android.database.Cursor
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.acessoaluno.DIA

class Generate(private val gw: SQLiteDatabase?) {
    fun registrarAluno(RGM:String,senha:String,aula:Array<Int>?){
        val cv = ContentValues()
        cv.put("rgm",RGM)
        cv.put("senha",senha)
        gw!!.insertOrThrow("Aluno", null, cv)
        if(aula != null){
            val cursor:Cursor = gw.rawQuery("SELECT rgm FROM Aluno WHERE rgm=$RGM",null)
            cursor.moveToFirst()
            if(cursor.count > 0){
                val rgmColumn = cursor.getColumnIndex("rgm")
                val id:Int = cursor.getInt(rgmColumn)
                for(actualAula in aula){
                    val cv2 = ContentValues()
                    cv2.put("aluno",id)
                    cv2.put("aula",actualAula)
                    gw!!.insertOrThrow("Aulas_dos_alunos",null,cv2)
                }
            }
            cursor.close()
        }
    }
    fun registrarAula(diaDaAula:DIA, professor:String, inicio:Int, fim:Int, materia:String):Boolean{
        val cv = ContentValues()
        cv.put("dia",diaDaAula.ordinal)
        cv.put("professor",professor)
        cv.put("inicio",inicio)
        cv.put("fim",fim)
        cv.put("materia",materia)
        return gw!!.insert("Aula",null,cv) > 0;
    }
}