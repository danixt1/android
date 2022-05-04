package com.example.acessoaluno.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class MakeActions(ctx:Context) {
    private val gw: Call?
    init {
        gw = Call.getInstance(ctx!!)
    }
    fun classFromUser(rgm:String):Cursor{
        val sql = "SELECT a.professor,a.inicio,a.fim,a.materia,a.dia,b.aluno,a.ID FROM Aula a JOIN Aulas_dos_alunos b ON b.aluno='$rgm' AND b.aula=a.ID"
        return gw!!.database.rawQuery(sql,null)
    }
    fun isValid(RGM: String,password:String):Boolean{
        return gw!!.database.rawQuery("SELECT ID,rgm,senha FROM Aluno WHERE rgm='$RGM' AND senha='$password'",null).count >0
    }
    fun RegisterIfNotRegistred(rgm: String,aula:Int):Boolean{
        val query = "SELECT ID FROM Presenca WHERE aluno='$rgm' AND aula=$aula and data=datetime('now')"
        val result = gw!!.database.rawQuery(query,null)
        if(result.count > 0){
            return false
        }else{
            val vals = ContentValues()
            vals.put("data","datetime('now')")
            vals.put("aluno",rgm)
            vals.put("aula",aula)
            gw!!.database.insert("Presenca",null,vals)
            return true
        }
    }
}