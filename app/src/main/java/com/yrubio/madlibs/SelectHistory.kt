package com.yrubio.madlibs

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class SelectHistory : AppCompatActivity() {
    private lateinit var storiesNames: Array<String>
    private lateinit var listStories: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_history)

        listStories = findViewById(R.id.listStories)

        fillList()
        listStories.setOnItemClickListener { _, _, position, _ ->

            val text = getStoryFile(position)
            val intent = Intent(baseContext, BlankSpaces::class.java)

            intent.putExtra("VALUE", text)
            startActivity(intent)
        }
    }

    private fun getStoryFile(position: Int): String {
        val scan = Scanner(
            resources.openRawResource(
                resources.getIdentifier(
                    storiesNames[position],
                    "raw",
                    packageName
                )
            )
        )
        var allText = ""
        while (scan.hasNextLine()) {
            val line = scan.nextLine()
            allText += line
        }
        scan.close()
        return allText
    }

    private fun fillList() {
        storiesNames = resources.getStringArray(R.array.stories_name)

        val selectTittle = storiesNames.map { text ->
            text.split("_")[1].let {
                it.substring(0, 1).toUpperCase(Locale.getDefault()) + it.substring(1)
            }
        }
        listStories.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, selectTittle)
    }
}