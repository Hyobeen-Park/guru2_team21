package com.example.guru2_team21

import android.content.Intent
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class MyrouteActivity : AppCompatActivity() {

    lateinit var choose_group : RadioGroup
    lateinit var myHelper : myDBHelper
    lateinit var sqlDB : SQLiteDatabase
    lateinit var route_layout : LinearLayout
    lateinit var route_recyclerview: RecyclerView
    lateinit var route_name : TextView
    lateinit var route_img : ImageView
    lateinit var route_address : TextView

    val routelist = ArrayList<routesData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myroute)
        setTitle("나만의 코스 만들기");

        myHelper = myDBHelper(this)

        route_recyclerview = findViewById(R.id.route_recyclerview)
        choose_group = findViewById(R.id.choose_group)
        route_layout = findViewById(R.id.route_layout)
        route_name = findViewById(R.id.route_name)
        route_img = findViewById(R.id.route_img)
        route_address = findViewById(R.id.route_address)

        choose_group.setOnCheckedChangeListener { group, checkId ->
            routelist.clear()
            when (checkId) {
                R.id.choose_sanggye -> show_list("sanggye")
                R.id.choose_junggye -> show_list("junggye")
                R.id.choose_hagye -> show_list("hagye")
                R.id.choose_wolgye -> show_list("wolgye")
                R.id.choose_gongneung -> show_list("gongneung")
            }
        }
    }

    private fun show_list(type : String){
        sqlDB = myHelper.readableDatabase
        var cursor : Cursor

        when(type){
            "sanggye" -> {
                cursor = sqlDB.rawQuery("SELECT * FROM placesTBL WHERE gArea Like 'sanggye';", null)
                while (cursor.moveToNext()) {
                    routelist.add(routesData(cursor.getString(1),cursor.getString(2)))
                }

                cursor = sqlDB.rawQuery("SELECT * FROM restaurantTBL WHERE gArea Like 'sanggye';", null)
                while (cursor.moveToNext()) {
                    routelist.add(routesData(cursor.getString(1),cursor.getString(2)))
                }

                cursor.close()
                sqlDB.close()
            }

            "junggye" -> {
                cursor = sqlDB.rawQuery("SELECT * FROM placesTBL WHERE gArea Like 'junggye';", null)
                while (cursor.moveToNext()) {
                    routelist.add(routesData(cursor.getString(1),cursor.getString(2)))
                }

                cursor = sqlDB.rawQuery("SELECT * FROM restaurantTBL WHERE gArea Like 'junggye';", null)
                while (cursor.moveToNext()) {
                    routelist.add(routesData(cursor.getString(1),cursor.getString(2)))
                }

                cursor.close()
                sqlDB.close()
            }

            "hagye" -> {
                cursor = sqlDB.rawQuery("SELECT * FROM placesTBL WHERE gArea Like 'hagye';", null)
                while (cursor.moveToNext()) {
                    routelist.add(routesData(cursor.getString(1),cursor.getString(2)))
                }

                cursor = sqlDB.rawQuery("SELECT * FROM restaurantTBL WHERE gArea Like 'hagye';", null)
                while (cursor.moveToNext()) {
                    routelist.add(routesData(cursor.getString(1),cursor.getString(2)))
                }

                cursor.close()
                sqlDB.close()
            }

            "wolgye" -> {
                cursor = sqlDB.rawQuery("SELECT * FROM placesTBL WHERE gArea Like 'wolgye';", null)
                while (cursor.moveToNext()) {
                    routelist.add(routesData(cursor.getString(1),cursor.getString(2)))
                }

                cursor = sqlDB.rawQuery("SELECT * FROM restaurantTBL WHERE gArea Like 'wolgye';", null)
                while (cursor.moveToNext()) {
                    routelist.add(routesData(cursor.getString(1),cursor.getString(2)))
                }

                cursor.close()
                sqlDB.close()
            }

            "gongneung" -> {
                cursor = sqlDB.rawQuery("SELECT * FROM placesTBL WHERE gArea Like 'gongneung';", null)
                while (cursor.moveToNext()) {
                    routelist.add(routesData(cursor.getString(1),cursor.getString(2)))
                }

                cursor = sqlDB.rawQuery("SELECT * FROM restaurantTBL WHERE gArea Like 'gongneung';", null)
                while (cursor.moveToNext()) {
                    routelist.add(routesData(cursor.getString(1),cursor.getString(2)))
                }

                cursor.close()
                sqlDB.close()
            }
        }

        val adapter = route_recyclerAdapter(routelist)

        adapter.setItemClickListener(object : route_recyclerAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                route_name.setText(routelist.get(position).name)
                route_address.setText(routelist.get(position).address)
                //이미지 추가
            }
        })
        route_recyclerview.adapter = adapter

    }

}