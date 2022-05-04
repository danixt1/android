package com.example.acessoaluno.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.acessoaluno.DIA

class CreateDatabase(private val context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    override fun onCreate(bd: SQLiteDatabase?) {
        val bufferReader = context.assets.open("database.sql").bufferedReader()
        val data = bufferReader.use {
            it.readText()
        }
        val querys = data.split(";")
        for(query in querys)
            bd!!.execSQL(query)
        val putVals = Generate(bd)

        putVals.registrarAula(DIA.SEGUNDA,"marcos",510,670,"Arquitetura de redes")
        putVals.registrarAula(DIA.QUARTA,"leandro",510,670,"Engenharia de software")
        putVals.registrarAula(DIA.QUINTA,"marcos",510,670,"Banco de dados")
        putVals.registrarAula(DIA.SEXTA,"marcos",510,570,"Analise de sistemas")

        putVals.registrarAula(DIA.SEGUNDA,"Mario",510,670,"Natação")
        putVals.registrarAula(DIA.TERCA,"Mario",510,670,"Academia")
        putVals.registrarAula(DIA.TERCA,"Mario",1200,1260,"Outra Aula")
        putVals.registrarAluno("24461096","umasenha",arrayOf(1,2,3,4))
        putVals.registrarAluno("13361096","senhaforte",arrayOf(5,6,7))
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        delete(p0)
        onCreate(p0)
    }
    private fun delete(p0: SQLiteDatabase?){
        val dele= arrayOf("Presenca","Aulas_dos_alunos","Aula","Aluno")
        for(n in dele){
            p0!!.execSQL("DROP TABLE IF EXISTS $n")
        }
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        const val DATABASE_NAME = "AcessoDoAluno.db"
        const val DATABASE_VERSION = 3
    }
}