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

    //지역별 리스트와 나만의 코스 리스트 선언
    val routelist = ArrayList<routesData>()
    val myroutelist = ArrayList<myroutesData>()

    //이미지 지정에 사용할 변수
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

        //선택한 라디오 버튼에 해당하는 리스트 보여주도록 구현
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

        //'리스트에 추가' 버튼을 클릭했을 경우
        route_add.setOnClickListener {
            myroutelist.add(myroutesData(route_name.getText().toString(), myroute_img))

            val myadapter = myroute_recyclerAdapter(myroutelist)

            //아이템 클릭시 AlertDialog를 이용하여 리스트에서 삭제가 가능하도록 구현
            myadapter.setItemClickListener(object : myroute_recyclerAdapter.OnItemClickListener{
                override fun onClick(v: View, position: Int){
                    val dlg: AlertDialog.Builder = AlertDialog.Builder(this@MyrouteActivity)
                    dlg.setTitle("삭제")
                    dlg.setMessage("리스트에서 삭제하시겠습니까?")
                    dlg.setIcon(R.mipmap.ic_launcher)
                    dlg.setPositiveButton("아니요", null)

                    //'예'를 클릭할 경우 Toast 메시지와 함께 리스트에서 삭제
                    dlg.setNegativeButton("예", DialogInterface.OnClickListener{dialog, which ->
                        Toast.makeText(this@MyrouteActivity, "리스트에서 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                        myroutelist.remove(myroutesData(myroutelist.get(position).name, myroutelist.get(position).img))
                        myroute_recyclerview.adapter = myadapter
                    })

                    dlg.show()
                }
            })
            myroute_recyclerview.adapter = myadapter
        }

        //'초기화' 버튼을 클릭했을 경우
        myroute_init.setOnClickListener {
            val myadapter = myroute_recyclerAdapter(myroutelist)

            myroutelist.clear()

            myroute_recyclerview.adapter = myadapter
        }

        //'선택 완료' 버튼을 클릭했을 경우
        //myroutelist전달과 함께 MyrouteCheckActivity로 액티비티 이동
        myroute_finish.setOnClickListener {
            val intent = Intent(this, MyrouteCheckActivity::class.java)
            intent.putExtra("myroutechecklist",myroutelist)
            startActivity(intent);
        }
    }

    //해당 지역의 리스트들을 보여주는 함수
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

        //아이템 클릭시 route_infoview에 해당하는 장소의 이름, 주소, 이미지 출력
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