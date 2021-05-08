package com.yrubio.madlibs

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

class BlankSpaces : AppCompatActivity() {

    private var words: List<String>? = null
    private var wordsUser = arrayListOf<String>()
    private var wordsNumber: Int = 0
    private lateinit var wordButton: AppCompatButton
    private lateinit var wordEditText: AppCompatEditText
    private lateinit var numberWordsTextView: TextView
    private lateinit var numberWordsInformation: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blank_spaces)

        wordButton = findViewById(R.id.wordButton)
        wordEditText = findViewById(R.id.wordEditText)
        numberWordsTextView = findViewById(R.id.numberWordsTextView)
        numberWordsInformation = findViewById(R.id.numberWordsInformation)

        val story = intent.getStringExtra("VALUE")

        words = story?.split("<")
            ?.filter { it.contains('>') }
            ?.map { it.substring(0, it.indexOf('>')).replace("-", " ") }
        wordsNumber = words?.size ?: 0
        fillPropertiesWords()

        wordButton.setOnClickListener {
            val input = wordEditText.text.toString()
            if (input.isNotEmpty()) {
                wordsUser.add(input)
                if (wordsNumber >= 1) {
                    fillPropertiesWords()
                    Toast.makeText(this, "great! keep going!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    var text = ""
                    var count = words!!.size
                    story?.split("<", ">")?.forEachIndexed { index, s ->
                        text += if (index % 2 == 0) s
                        else {
                            count--
                            wordsUser[count]
                        }
                    }
                    val intent = Intent(baseContext, FinalText::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    intent.putExtra("VALUE", text)
                    startActivity(intent)
                }
            } else {
                Toast.makeText(this, "is empty!", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun fillPropertiesWords() {
        numberWordsTextView.text = "$wordsNumber ${getString(R.string.words_left)}"
        wordEditText.text?.clear()
        wordEditText.hint = words!![wordsNumber - 1]
        numberWordsInformation.text =
            "${getString(R.string.please_type)} ${words!![wordsNumber - 1]}"
        wordsNumber--
    }
}