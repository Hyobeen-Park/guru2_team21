package com.example.guru2_team21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Myroutelist : AppCompatActivity() {

    lateinit var check_list_1 : Button
    lateinit var check_list_2 : Button
    lateinit var check_list_3 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myroutelist)
        setTitle("나만의 코스 목록 확인");

        check_list_1 = findViewById(R.id.check_list_1)
        check_list_2 = findViewById(R.id.check_list_2)
        check_list_3 = findViewById(R.id.check_list_3)

        check_list_1.setOnClickListener {

        }

        check_list_2.setOnClickListener {

        }

        check_list_3.setOnClickListener {

        }

    }
}