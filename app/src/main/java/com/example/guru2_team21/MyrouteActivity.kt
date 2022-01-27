package com.example.guru2_team21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioGroup

class MyrouteActivity : AppCompatActivity() {

    lateinit var choose_group : RadioGroup
    lateinit var choose_img : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myroute)
        setTitle("나만의 코스 만들기");

        choose_group = findViewById(R.id.choose_group)
        choose_img = findViewById(R.id.choose_img)

        choose_group.setOnCheckedChangeListener{group, checkId ->
            when(checkId){
                R.id.choose_sanggye -> choose_img.setImageResource(R.drawable.sanggye)
                R.id.choose_junggye -> choose_img.setImageResource(R.drawable.junggye)
                R.id.choose_hagye -> choose_img.setImageResource(R.drawable.hagye)
                R.id.choose_wolgye -> choose_img.setImageResource(R.drawable.wolgye)
                R.id.choose_gongneung -> choose_img.setImageResource(R.drawable.gongneung)
        } }



    }
}