package com.example.guru2_team21

import android.content.Intent
import android.content.Context
import android.content.DialogInterface
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class MyrouteActivity : AppCompatActivity() {

    lateinit var choose_group : RadioGroup
    lateinit var myHelper : myDBHelper
    lateinit var sqlDB : SQLiteDatabase
    lateinit var route_layout : LinearLayout
    lateinit var route_infoview : LinearLayout
    lateinit var route_recyclerview: RecyclerView
    lateinit var myroute_recyclerview : RecyclerView
    lateinit var route_name : TextView
    lateinit var route_img : ImageView
    lateinit var route_address : TextView
    lateinit var route_add : Button
    lateinit var myroute_init : Button
    lateinit var myroute_finish : Button

    val routelist = ArrayList<routesData>()
    val myroutelist = ArrayList<myroutesData>()

    var myroute_img : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myroute)
        setTitle("나만의 코스 만들기")

        myHelper = myDBHelper(this)
        myroutelist.clear()

        route_recyclerview = findViewById(R.id.route_recyclerview)
        myroute_recyclerview = findViewById(R.id.myroute_recyclerview)
        choose_group = findViewById(R.id.choose_group)
        route_layout = findViewById(R.id.route_layout)
        route_infoview = findViewById(R.id.route_infoview)
        route_name = findViewById(R.id.route_name)
        route_img = findViewById(R.id.route_img)
        route_address = findViewById(R.id.route_address)
        route_add = findViewById(R.id.route_add)
        myroute_init = findViewById(R.id.myroute_init)
        myroute_finish = findViewById(R.id.myroute_finish)

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

        route_add.setOnClickListener {
            myroutelist.add(myroutesData(route_name.getText().toString(), myroute_img))

            val myadapter = myroute_recyclerAdapter(myroutelist)
            myadapter.setItemClickListener(object : myroute_recyclerAdapter.OnItemClickListener{
                override fun onClick(v: View, position: Int){
                    val dlg: AlertDialog.Builder = AlertDialog.Builder(this@MyrouteActivity)
                    dlg.setTitle("삭제")
                    dlg.setMessage("리스트에서 삭제하시겠습니까?")
                    dlg.setIcon(R.mipmap.ic_launcher)
                    dlg.setPositiveButton("아니요", null)
                    dlg.setNegativeButton("예", DialogInterface.OnClickListener{dialog, which ->
                        Toast.makeText(this@MyrouteActivity, "리스트에서 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                        //myroutelist.remove(myroutesData(myroutelist.get(position).name))
                        myroute_recyclerview.adapter = myadapter
                    })
                    dlg.show()
                }
            })
            myroute_recyclerview.adapter = myadapter
        }

        myroute_init.setOnClickListener {
            val myadapter = myroute_recyclerAdapter(myroutelist)

            myroutelist.clear()

            myroute_recyclerview.adapter = myadapter
        }

        myroute_finish.setOnClickListener {
            val intent = Intent(this, MyrouteCheckActivity::class.java)
            intent.putExtra("myroutechecklist",myroutelist)
            startActivity(intent);
        }
    }

    private fun show_list(type : String){
        sqlDB = myHelper.readableDatabase
        var cursor : Cursor

        when(type){
            "sanggye" -> {
                cursor = sqlDB.rawQuery("SELECT * FROM placesTBL WHERE gArea Like 'sanggye';", null)
                while (cursor.moveToNext()) {
                    routelist.add(routesData(cursor.getString(1),cursor.getString(2),resources.getIdentifier(cursor.getString(4), "drawable", this.packageName)))
                }

                cursor = sqlDB.rawQuery("SELECT * FROM restaurantTBL WHERE gArea Like 'sanggye';", null)
                while (cursor.moveToNext()) {
                    routelist.add(routesData(cursor.getString(1),cursor.getString(2),resources.getIdentifier(cursor.getString(4), "drawable", this.packageName)))
                }

                cursor.close()
                sqlDB.close()
            }

            "junggye" -> {
                cursor = sqlDB.rawQuery("SELECT * FROM placesTBL WHERE gArea Like 'junggye';", null)
                while (cursor.moveToNext()) {
                    routelist.add(routesData(cursor.getString(1),cursor.getString(2),resources.getIdentifier(cursor.getString(4), "drawable", this.packageName)))
                }

                cursor = sqlDB.rawQuery("SELECT * FROM restaurantTBL WHERE gArea Like 'junggye';", null)
                while (cursor.moveToNext()) {
                    routelist.add(routesData(cursor.getString(1),cursor.getString(2),resources.getIdentifier(cursor.getString(4), "drawable", this.packageName)))
                }

                cursor.close()
                sqlDB.close()
            }

            "hagye" -> {
                cursor = sqlDB.rawQuery("SELECT * FROM placesTBL WHERE gArea Like 'hagye';", null)
                while (cursor.moveToNext()) {
                    routelist.add(routesData(cursor.getString(1),cursor.getString(2),resources.getIdentifier(cursor.getString(4), "drawable", this.packageName)))
                }

                cursor = sqlDB.rawQuery("SELECT * FROM restaurantTBL WHERE gArea Like 'hagye';", null)
                while (cursor.moveToNext()) {
                    routelist.add(routesData(cursor.getString(1),cursor.getString(2),resources.getIdentifier(cursor.getString(4), "drawable", this.packageName)))
                }

                cursor.close()
                sqlDB.close()
            }

            "wolgye" -> {
                cursor = sqlDB.rawQuery("SELECT * FROM placesTBL WHERE gArea Like 'wolgye';", null)
                while (cursor.moveToNext()) {
                    routelist.add(routesData(cursor.getString(1),cursor.getString(2),resources.getIdentifier(cursor.getString(4), "drawable", this.packageName)))
                }

                cursor = sqlDB.rawQuery("SELECT * FROM restaurantTBL WHERE gArea Like 'wolgye';", null)
                while (cursor.moveToNext()) {
                    routelist.add(routesData(cursor.getString(1),cursor.getString(2),resources.getIdentifier(cursor.getString(4), "drawable", this.packageName)))
                }

                cursor.close()
                sqlDB.close()
            }

            "gongneung" -> {
                cursor = sqlDB.rawQuery("SELECT * FROM placesTBL WHERE gArea Like 'gongneung';", null)
                while (cursor.moveToNext()) {
                    routelist.add(routesData(cursor.getString(1),cursor.getString(2),resources.getIdentifier(cursor.getString(4), "drawable", this.packageName)))
                }

                cursor = sqlDB.rawQuery("SELECT * FROM restaurantTBL WHERE gArea Like 'gongneung';", null)
                while (cursor.moveToNext()) {
                    routelist.add(routesData(cursor.getString(1),cursor.getString(2),resources.getIdentifier(cursor.getString(4), "drawable", this.packageName)))
                }

                cursor.close()
                sqlDB.close()
            }
        }

        val adapter = route_recyclerAdapter(routelist)

        adapter.setItemClickListener(object : route_recyclerAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                route_infoview.setVisibility(View.VISIBLE)
                route_name.setText(routelist.get(position).name)
                route_address.setText(routelist.get(position).address)
                route_img.setImageResource(resources.getIdentifier(routelist.get(position).img.toString(),"drawable",routelist.get(position).name))

                myroute_img = routelist.get(position).img
            }
        })
        route_recyclerview.adapter = adapter

    }

}