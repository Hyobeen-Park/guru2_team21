package com.example.guru2_team21

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SearchActivity : AppCompatActivity() {
    var search_area = "nowon"   //검색 지역
    var search_type = "place"   //장소 or 식당

    lateinit var search_place: TextView
    lateinit var search_restaurant: TextView
    lateinit var search_sanggye: ImageView
    lateinit var search_junggye: ImageView
    lateinit var search_hagye: ImageView
    lateinit var search_wolgye: ImageView
    lateinit var search_gongneung: ImageView
    lateinit var search_by_name: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        search_place = findViewById(R.id.search_place)
        search_restaurant = findViewById(R.id.search_restaurant)
        search_sanggye = findViewById(R.id.search_Sanggye)
        search_junggye = findViewById(R.id.search_Junggye)
        search_hagye = findViewById(R.id.search_Hagye)
        search_wolgye = findViewById(R.id.search_Wolgye)
        search_gongneung = findViewById(R.id.search_Gongneung)
        search_by_name = findViewById(R.id.search_by_name)

        print_info(search_type, search_area)

        //장소 or 식당
        search_place.setOnClickListener {
            search_place.setTextColor(Color.BLACK)
            search_restaurant.setTextColor(Color.GRAY)
            search_type = "place"
            print_info(search_type, search_area)
        }

        search_restaurant.setOnClickListener {
            search_place.setTextColor(Color.GRAY)
            search_restaurant.setTextColor(Color.BLACK)
            search_type = "restaurant"
            print_info(search_type, search_area)
        }

        //이름으로 검색
        search_by_name.setOnClickListener {
            val dlg = SearchDialog(this)
            dlg.start()
        }

        //검색지역 변경
        search_sanggye.setOnClickListener {
            if(search_area.equals("sanggye")) {
                search_area = "nowon"
                manageImage()
                print_info(search_type, search_area)
            } else {
                search_area = "sanggye"
                manageImage()
                print_info(search_type, search_area)
            }
        }

        search_junggye.setOnClickListener {
            if(search_area.equals("junggye")) {
                search_area = "nowon"
                manageImage()
                print_info(search_type, search_area)
            } else {
                search_area = "junggye"
                manageImage()
                print_info(search_type, search_area)
            }
        }

        search_hagye.setOnClickListener {
            if(search_area.equals("hagye")) {
                search_area = "nowon"
                manageImage()
                print_info(search_type, search_area)
            } else {
                search_area = "hagye"
                manageImage()
                print_info(search_type, search_area)
            }
        }

        search_wolgye.setOnClickListener {
            if(search_area.equals("wolgye")) {
                search_area = "nowon"
                manageImage()
                print_info(search_type, search_area)
            } else {
                search_area = "wolgye"
                manageImage()
                print_info(search_type, search_area)
            }
        }

        search_gongneung.setOnClickListener {
            if(search_area.equals("gongneung")) {
                search_area = "nowon"
                manageImage()
                print_info(search_type, search_area)
            } else {
                search_area = "gongneung"
                manageImage()
                print_info(search_type, search_area)
            }
        }

    }

    private fun print_info(search_type: String, search_area: String) {
        //정보 search_layout 에 추가하기

    }

    private fun manageImage() {
        when {
            search_area.equals("nowon") -> {
                search_sanggye.drawable.setAlpha(255)
                search_junggye.drawable.setAlpha(255)
                search_hagye.drawable.setAlpha(255)
                search_wolgye.drawable.setAlpha(255)
                search_gongneung.drawable.setAlpha(255)
            }

            search_area.equals("sanggye") -> {
                search_sanggye.drawable.setAlpha(255)
                search_junggye.drawable.setAlpha(50)
                search_hagye.drawable.setAlpha(50)
                search_wolgye.drawable.setAlpha(50)
                search_gongneung.drawable.setAlpha(50)
            }

            search_area.equals("junggye") -> {
                search_sanggye.drawable.setAlpha(50)
                search_junggye.drawable.setAlpha(255)
                search_hagye.drawable.setAlpha(50)
                search_wolgye.drawable.setAlpha(50)
                search_gongneung.drawable.setAlpha(50)
            }

            search_area.equals("hagye") -> {
                search_sanggye.drawable.setAlpha(50)
                search_junggye.drawable.setAlpha(50)
                search_hagye.drawable.setAlpha(255)
                search_wolgye.drawable.setAlpha(50)
                search_gongneung.drawable.setAlpha(50)
            }

            search_area.equals("wolgye") -> {
                search_sanggye.drawable.setAlpha(50)
                search_junggye.drawable.setAlpha(50)
                search_hagye.drawable.setAlpha(50)
                search_wolgye.drawable.setAlpha(255)
                search_gongneung.drawable.setAlpha(50)
            }

            search_area.equals("gongneung") -> {
                search_sanggye.drawable.setAlpha(50)
                search_junggye.drawable.setAlpha(50)
                search_hagye.drawable.setAlpha(50)
                search_wolgye.drawable.setAlpha(50)
                search_gongneung.drawable.setAlpha(255)
            }
        }

    }



}