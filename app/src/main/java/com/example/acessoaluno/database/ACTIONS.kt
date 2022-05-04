package com.example.acessoaluno.database

enum class ACTIONS(val sql:String) {

    SQL_CREATE_TABLE_ALUNO("CREATE TABLE Aluno("+
            "ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "rgm TEXT NOT NULL UNIQUE," +
            "senha TEXT NOT NULL);"),
    SQL_CREATE_TABLE_Aula("CREATE TABLE Aula("+
            "ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "dia INTEGER NOT NULL,"+
            "professor TEXT NOT NULL,"+
            "inicio INTEGER NOT NULL,"+
            "fim INTEGER NOT NULL,"+
            "sala TEXT," +
            "bloco TEXT," +
            "materia TEXT NOT NULL );"),

    SQL_CREATE_TABLE_Aulas_dos_alunos("CREATE TABLE Aulas_dos_alunos(" +
            "ID iNTEGER PRIMARY KEY AUTOINCREMENT," +
            "aluno TEXT NOT NULL," +
            "aula INTEGER NOT NULL," +
            "FOREIGN KEY(aluno) REFERENCES Aluno(rgm)," +
            "FOREIGN KEY(aula) REFERENCES Aula(ID));"),
    SQL_CREATE_TABLE_presenca("CREATE TABLE Presenca(" +
            "data DATE NOT NULL," +
            "aluno INTEGER NOT NULL," +
            "aula INTEGER NOT NULL," +
            "FOREIGN Key(aluno) REFERENCES Aluno(ID)," +
            "FOREIGN KEY(aula) REFERENCES Aula(ID));"
    )
}