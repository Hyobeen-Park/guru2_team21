package com.example.guru2_team21

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var search : Button
    lateinit var record : Button
    lateinit var rec_route : Button
    lateinit var my_route : Button
    lateinit var maintext : TextView
    lateinit var subtext : TextView
    lateinit var place_logo : ImageView
    lateinit var start_school : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search = findViewById(R.id.search)
        record = findViewById(R.id.record)
        rec_route = findViewById(R.id.rec_route)
        my_route = findViewById(R.id.my_route)
        maintext = findViewById(R.id.Maintext)
        subtext = findViewById(R.id.Subtext)
        place_logo = findViewById(R.id.place_logo)
        start_school = findViewById(R.id.start_school)

        search.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        record.setOnClickListener {
            val intent = Intent(this, RecordActivity::class.java)
            startActivity(intent)
        }

        rec_route.setOnClickListener {

        }

        my_route.setOnClickListener {
            val intent = Intent(this, MyrouteActivity::class.java)
            startActivity(intent)
        }

    }
}