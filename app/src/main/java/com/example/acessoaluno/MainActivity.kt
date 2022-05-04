package com.example.acessoaluno

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.acessoaluno.database.MakeActions

class MainActivity : AppCompatActivity() {
    private lateinit var user:EditText
    private lateinit var password:EditText
    private lateinit var btnEnter:Button
    private lateinit var btnExit:Button
    private lateinit var select: MakeActions
    private lateinit var textError:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        select = MakeActions(baseContext)
        user = findViewById(R.id.user)
        password = findViewById(R.id.password)
        btnEnter = findViewById(R.id.acess)
        btnExit = findViewById(R.id.exit)
        textError = findViewById(R.id.failMessage)

        user.setText("24461096")
        password.setText("umasenha")

        btnExit.setOnClickListener {
            finish()
            this.moveTaskToBack(true)
        }
        btnEnter.setOnClickListener {
            val userText:String = user.text.toString()
            val passwordText = password.text.toString()
            if(select.isValid(userText,passwordText)){
                val informacoes = Intent(this,Informacoes::class.java).apply{
                    putExtra("rgm",userText)
                }
                startActivity(informacoes)
            }else{
                password.text.clear()
                textError.setText(R.string.user_invalid)
            }
        }
    }
}