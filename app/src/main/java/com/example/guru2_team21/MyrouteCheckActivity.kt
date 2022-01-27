package com.example.guru2_team21

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MyrouteCheckActivity : AppCompatActivity() {

    lateinit var s_icon : ImageView
    lateinit var s_arrow_icon : ImageView
    lateinit var s_circle1 : ImageView
    lateinit var s_circle2 : ImageView
    lateinit var s_circle3 : ImageView
    lateinit var s_circle4 : ImageView
    lateinit var s_circle5 : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myroute_check)
        setTitle("나만의 최종 여행코스");

        s_icon = findViewById(R.id.s_icon)
        s_arrow_icon = findViewById(R.id.s_arrow_icon)
        s_circle1 = findViewById(R.id.s_circle1)
        s_circle2 = findViewById(R.id.s_circle2)
        s_circle3 = findViewById(R.id.s_circle3)
        s_circle4 = findViewById(R.id.s_circle4)
        s_circle5 = findViewById(R.id.s_circle5)
    }
}