package com.yrubio.madlibs

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<AppCompatButton>(R.id.startButton)
        startButton.setOnClickListener {
            val intent = Intent(baseContext, SelectHistory::class.java)
            startActivity(intent)
        }
    }
}