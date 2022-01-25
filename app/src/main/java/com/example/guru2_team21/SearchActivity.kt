package com.example.guru2_team21

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SearchActivity : AppCompatActivity() {
    var search_area = "nowon"
    var search_type = "place"

    lateinit var search_place: TextView
    lateinit var search_restaurant: TextView
    lateinit var search_sanggye: ImageView
    lateinit var search_junggye: ImageView
    lateinit var search_hagye: ImageView
    lateinit var search_wolgye: ImageView
    lateinit var search_gongneung: ImageView

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

        search_sanggye.setOnClickListener {
            if(search_area.equals("sanggye")) {
                search_area = "nowon"
                //크기 20 줄이기
            }
        }
    }

    private fun print_info(search_type: String, search_area: String) {
        //정보 search_layout 에 추가하기
    }



}