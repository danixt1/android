CREATE TABLE Aluno(
    "ID" INTEGER PRIMARY KEY AUTOINCREMENT,
    "rgm" TEXT NOT NULL UNIQUE,
    "senha" TEXT NOT NULL
);

CREATE TABLE Aula(
    "ID" INTEGER PRIMARY KEY AUTOINCREMENT,
    "dia" INTEGER NOT NULL,
    "professor" TEXT NOT NULL,
    "inicio" INTEGER NOT NULL,
    "fim" INTEGER NOT NULL,
    "sala" TEXT,
    "bloco" TEXT,
    "materia" TEXT NOT NULL
);

CREATE TABLE Aulas_dos_alunos(
    "ID" iNTEGER PRIMARY KEY AUTOINCREMENT,
    "aluno" TEXT NOT NULL,
    "aula" INTEGER NOT NULL,
    FOREIGN KEY(aluno) REFERENCES Aluno(rgm),
    FOREIGN KEY(aula) REFERENCES Aula(ID)
);
CREATE TABLE Presenca(
    "ID" INTEGER PRIMARY KEY AUTOINCREMENT,
    "data" DATE NOT NULL,
    "aluno" TEXT NOT NULL,
    "aula" INTEGER NOT NULL,
    FOREIGN Key(aluno) REFERENCES Aluno(rgm),
    FOREIGN KEY(aula) REFERENCES Aula(ID))