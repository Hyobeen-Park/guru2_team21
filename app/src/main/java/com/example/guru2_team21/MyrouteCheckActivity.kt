package com.example.guru2_team21

import android.database.sqlite.SQLiteDatabase
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyrouteCheckActivity : AppCompatActivity() {

    lateinit var myHelper : myDBHelper
    lateinit var sqlDB : SQLiteDatabase

    lateinit var myroute_check_recyclerview : RecyclerView
    lateinit var route_check_layout : LinearLayout

    var myroutechecklist = ArrayList<myroutesCheckData>()
    var myroutelist = ArrayList<myroutesData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myroute_check)
        setTitle("나만의 최종 여행코스")

        val intent = getIntent()
        //cast 안됨
        myroutechecklist = intent.getSerializableExtra("myroutechecklist") as ArrayList<myroutesCheckData>


        myHelper = myDBHelper(this)

        route_check_layout = findViewById(R.id.myroute_check_layout)
        myroute_check_recyclerview = findViewById(R.id.myroute_check_recyclerview)


        val mycheckadapter = myroute_check_recyclerAdapter(myroutechecklist)

        myroute_check_recyclerview.adapter = mycheckadapter

    }
}