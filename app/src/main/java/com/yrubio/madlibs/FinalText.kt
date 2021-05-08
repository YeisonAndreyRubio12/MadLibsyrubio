package com.yrubio.madlibs

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton


class FinalText : AppCompatActivity() {

    private lateinit var storyTextView: TextView
    private lateinit var makeAnotherButton: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_text)

        storyTextView = findViewById(R.id.storyTextView)
        makeAnotherButton = findViewById(R.id.makeAnotherButton)

        storyTextView.text = intent.getStringExtra("VALUE")
        storyTextView.movementMethod = ScrollingMovementMethod()
        makeAnotherButton.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()

        }
    }
}