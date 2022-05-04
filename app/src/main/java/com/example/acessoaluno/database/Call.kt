package com.example.acessoaluno.database
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class Call constructor(ctx: Context) {
    val database: SQLiteDatabase

    companion object {
        private var gw: Call? = null
        fun getInstance(ctx: Context): Call? {
            if (gw == null) {
                gw = Call(ctx)
            }
            return gw
        }
    }

    init {
        val helper = CreateDatabase(ctx)
        database = helper.writableDatabase
    }
}