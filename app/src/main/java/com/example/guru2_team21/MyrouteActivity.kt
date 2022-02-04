package com.example.guru2_team21

import android.content.Intent
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class MyrouteActivity : AppCompatActivity() {

    lateinit var choose_group : RadioGroup
    lateinit var choose_img : ImageView
    lateinit var myHelper : myDBHelper
    lateinit var sqlDB : SQLiteDatabase
    lateinit var route_layout : LinearLayout
    lateinit var route_recyclerview: RecyclerView

    var list_str = ""
    var list_name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myroute)
        setTitle("나만의 코스 만들기");

        myHelper = myDBHelper(this)
        sqlDB = myHelper.readableDatabase
        var cursor : Cursor
        route_recyclerview = findViewById(R.id.route_recyclerview)
        choose_group = findViewById(R.id.choose_group)
        route_layout = findViewById(R.id.route_layout)

        val routelist = ArrayList<routesData>()
        val adapter = route_recyclerAdapter(routelist)
        route_recyclerview.adapter = adapter

        choose_group.setOnCheckedChangeListener { group, checkId ->
            when (checkId) {
                R.id.choose_sanggye -> {
                    cursor = sqlDB.rawQuery("SELECT * FROM placesTBL WHERE gArea Like 'sanggye';", null)
                    while (cursor.moveToNext()) {
                        routelist.add(routesData(cursor.getString(1)))
                    }

                    cursor = sqlDB.rawQuery("SELECT * FROM restaurantTBL WHERE gArea Like 'sanggye';", null)
                    while (cursor.moveToNext()) {

                    }

                    cursor.close()
                    sqlDB.close()
                }

                R.id.choose_junggye -> {
                    cursor = sqlDB.rawQuery("SELECT * FROM placesTBL WHERE gArea Like 'junggye';", null)
                    while (cursor.moveToNext()) {

                    }

                    cursor = sqlDB.rawQuery("SELECT * FROM restaurantTBL WHERE gArea Like 'junggye';", null)
                    while (cursor.moveToNext()) {

                    }

                    cursor.close()
                    sqlDB.close()
                }

                R.id.choose_hagye -> {
                    cursor = sqlDB.rawQuery("SELECT * FROM placesTBL WHERE gArea Like 'hagye';", null)
                    while (cursor.moveToNext()) {

                    }

                    cursor = sqlDB.rawQuery("SELECT * FROM restaurantTBL WHERE gArea Like 'hagye';", null)
                    while (cursor.moveToNext()) {

                    }

                    cursor.close()
                    sqlDB.close()
                }

                R.id.choose_wolgye -> {
                    cursor = sqlDB.rawQuery("SELECT * FROM placesTBL WHERE gArea Like 'wolgye';", null)
                    while (cursor.moveToNext()) {

                    }

                    cursor = sqlDB.rawQuery("SELECT * FROM restaurantTBL WHERE gArea Like 'wolgye';", null)
                    while (cursor.moveToNext()) {

                    }

                    cursor.close()
                    sqlDB.close()
                }

                R.id.choose_gongneung -> {
                    cursor = sqlDB.rawQuery("SELECT * FROM placesTBL WHERE gArea Like 'gongneung';", null)
                    while (cursor.moveToNext()) {

                    }

                    cursor = sqlDB.rawQuery("SELECT * FROM restaurantTBL WHERE gArea Like 'gongneung';", null)
                    while (cursor.moveToNext()) {

                    }

                    cursor.close()
                    sqlDB.close()
                }
            }
            route_layout.removeAllViews()
            routelist.clear()

        }
    }

}