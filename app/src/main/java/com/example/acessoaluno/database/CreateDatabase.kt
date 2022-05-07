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
        putVals.registrarAula(DIA.QUINTA,"Alexandre",510,670,"Banco de dados")
        putVals.registrarAula(DIA.SEXTA,"marcos",510,570,"Analise de sistemas")

        putVals.registrarAula(DIA.SEGUNDA,"Mario",510,670,"Neuropediatria")
        putVals.registrarAula(DIA.TERCA,"Ana Maria",510,670,"Genética")
        putVals.registrarAula(DIA.QUINTA,"Mario",1200,1260,"Neonatologia")
        putVals.registrarAula(DIA.TERCA,"Mario",1200,1260,"Medicina da Adolescência")

        putVals.registrarAula(DIA.TERCA,"Marcos",0,1260,"Aula de teste terça")
        putVals.registrarAula(DIA.QUINTA,"Marcos",0,1260,"Aula de teste quinta")
        putVals.registrarAula(DIA.SEXTA,"Marcos",0,1440,"Aula de teste Sexta")
        putVals.registrarAula(DIA.SABADO,"Alexandre",0,60,"Aula de teste sabado")
        putVals.registrarAula(DIA.SABADO,"Alexandre",60,700,"Aula de teste sabado 2")
        putVals.registrarAula(DIA.SABADO,"Alex",700,1440,"Aula de teste sabado 3")

        putVals.registrarAula(DIA.SEGUNDA,"Ecila Alves",510,585,"FUNDAMENTOS DE INTELIGÊNCIA ARTIFICIAL")
        putVals.registrarAula(DIA.SEGUNDA,"Ecila Alves",595,670,"FUNDAMENTOS DE INTELIGÊNCIA ARTIFICIAL")
        putVals.registrarAula(DIA.QUARTA,"Alexandre Leite",510,585,"PROGRAMAÇÃO PARA DISPOSITIVOS MÓVEIS")
        putVals.registrarAula(DIA.QUARTA,"Alexandre Leite",595,670,"PROGRAMAÇÃO PARA DISPOSITIVOS MÓVEIS")
        putVals.registrarAula(DIA.QUINTA,"Rodrigo Rodrigues",510,585,"TRABALHO DE GRADUAÇÃO INTERDISCIPLINAR I")
        putVals.registrarAula(DIA.SEXTA,"Juliano Ratusznei",510,585,"LINGUAGENS FORMAIS E AUTÔMATOS")
        putVals.registrarAula(DIA.SEXTA,"Juliano Ratusznei",595,670,"LINGUAGENS FORMAIS E AUTÔMATOS")

        putVals.registrarAluno("24461096","umasenha",arrayOf(1,2,3,4))
        putVals.registrarAluno("13361096","senhaforte",arrayOf(5,6,7,8))
        putVals.registrarAluno("teste","teste",arrayOf(9,10,11,12,13,14))
        putVals.registrarAluno("21342581","senhasimples",arrayOf(15,16,17,18,19,20,21))
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