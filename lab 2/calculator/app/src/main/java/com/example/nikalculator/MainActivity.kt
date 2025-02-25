package com.example.nikalculator

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()

        val rot : VideoView = findViewById(R.id.videoView)

        val vidUri: Uri = Uri.parse("android.resource://${packageName}/${R.raw.rot}")

        rot.setVideoURI(vidUri)

        rot.setOnPreparedListener{
            rot.start()
        }

        rot.setOnCompletionListener {
            rot.start()
        }



        val num1 : EditText = findViewById(R.id.num1)
        val num2 : EditText = findViewById(R.id.num2)
        val finalres : TextView = findViewById(R.id.res)
        var num1val : Double
        var num2val : Double
        var finalresval : Double = 0.0
        var operator : String = "NULL"

        val add : Button = findViewById(R.id.add)
        val sub : Button = findViewById(R.id.sub)
        val mul : Button = findViewById(R.id.mul)
        val div : Button = findViewById(R.id.div)
        val res : Button = findViewById(R.id.result)

        add.setOnClickListener{
            operator = "+"
            println("ADD")
        }
        sub.setOnClickListener{
            operator = "-"
        }
        mul.setOnClickListener{
            operator = "*"
        }
        div.setOnClickListener{
            operator = "/"
        }

        res.setOnClickListener{
            num1val = num1.text.toString().toDouble()
            num2val = num2.text.toString().toDouble()

           when(operator){
               "+" -> finalresval = num1val + num2val
               "-" -> finalresval = num1val - num2val
               "*" -> finalresval = num1val * num2val
               "/" -> if(num2val==0.0) finalresval = 0.0 else finalresval = num1val / num2val
               else ->{
                   finalres.text = "Dumbass"
               }
           }
            finalres.text = finalresval.toString()
        }







    }



}