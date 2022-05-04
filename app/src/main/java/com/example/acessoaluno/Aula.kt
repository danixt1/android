package com.example.acessoaluno

class Aula(val professor:String,var _inicio:Int,var _fim:Int,val materia:String,val id:Int) {
    val inicio:String = turnIntime(getTime(_inicio))
    val fim:String =turnIntime(getTime(_fim))
    private fun turnIntime(value:String):String{
        val havePoint = value.indexOf(".")
        var finalString = ""
        if(havePoint != -1){
            if(havePoint == 1){
                finalString+="0"
            }
            finalString+=value.slice(IntRange(0,havePoint -1)) + ":" + value[havePoint+1] + "0"
        }else{
            if(value.length == 1){
                finalString+="0"
            }
            finalString+= "$value:00"
        }
        return finalString
    }
    private fun getTime(_value:Int):String{
        var value = _value
        var horas = 0
        while(value >= 60){
            horas++
            value -=60
        }
        return "$horas.$value"
    }
}