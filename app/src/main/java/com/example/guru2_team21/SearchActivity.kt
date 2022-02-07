package com.example.guru2_team21

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class SearchActivity : AppCompatActivity() {
    lateinit var search_area : String   //검색 지역         nowon / sanggye / junggye / hagye / wolgye / gongneung
    lateinit var search_type : String   //장소 or 식당      place / restaurant

    lateinit var search_place: TextView
    lateinit var search_restaurant: TextView
    lateinit var search_sanggye: ImageView
    lateinit var search_junggye: ImageView
    lateinit var search_hagye: ImageView
    lateinit var search_wolgye: ImageView
    lateinit var search_gongneung: ImageView
    lateinit var search_by_name: ImageView
    lateinit var search_recyclerView: RecyclerView

    lateinit var myHelper: myDBHelper
    lateinit var sqlDB: SQLiteDatabase

    val placeList = ArrayList<placesData>()     //recyclerView list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setTitle("검색하기")

        search_place = findViewById(R.id.search_place)
        search_restaurant = findViewById(R.id.search_restaurant)
        search_sanggye = findViewById(R.id.search_Sanggye)
        search_junggye = findViewById(R.id.search_Junggye)
        search_hagye = findViewById(R.id.search_Hagye)
        search_wolgye = findViewById(R.id.search_Wolgye)
        search_gongneung = findViewById(R.id.search_Gongneung)
        search_by_name = findViewById(R.id.search_by_name)

        search_recyclerView = findViewById(R.id.search_recyclerView)

        myHelper = myDBHelper(this)

        search_type = "place"
        search_area = "nowon"
        manageImage()
        print_info(search_type, search_area)

        //장소 or 식당 구분하기
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
            sqlDB = myHelper.readableDatabase
            var cursor: Cursor

            val dlg = SearchDialog(this)
            dlg.start()
            dlg.setOnClickListener(object : SearchDialog.OnDialogClickListener {
                override fun onClicked(name: String) {
                    cursor = sqlDB.rawQuery("SELECT * FROM placesTBL WHERE gName LIKE '%'||'$name'||'%';", null)
                    placeList.clear()

                    while (cursor.moveToNext()) {
                        placeList.add(placesData(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                                resources.getIdentifier(cursor.getString(4), "drawable", packageName)))
                    }

                    cursor.close()
                    sqlDB.close()

                    if(placeList.size == 0) {
                        print_toast()
                    }
                }
            })
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

    //정보 search_layout 에 추가하기
    private fun print_info(search_type: String, search_area: String) {
        sqlDB = myHelper.readableDatabase
        var cursor: Cursor

        placeList.clear()

        if (search_type.equals("place")) {      //장소 검색
            when(search_area) {
                "nowon" -> {    //노원구 전체 검색
                    cursor = sqlDB.rawQuery("SELECT * FROM placesTBL;", null)

                    while (cursor.moveToNext()) {
                        placeList.add(placesData(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                                resources.getIdentifier(cursor.getString(4), "drawable", this.packageName)))
                    }

                    cursor.close()
                    sqlDB.close()
                }

                "sanggye" -> {    //상계 검색
                    cursor = sqlDB.rawQuery("SELECT * FROM placesTBL WHERE gArea LIKE 'sanggye';", null)
                    while (cursor.moveToNext()) {
                        placeList.add(placesData(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                                resources.getIdentifier(cursor.getString(4), "drawable", this.packageName)))
                    }

                    cursor.close()
                    sqlDB.close()
                }

                "junggye" -> {    //중계 검색
                    cursor = sqlDB.rawQuery("SELECT * FROM placesTBL WHERE gArea LIKE 'junggye';", null)
                    while (cursor.moveToNext()) {
                        placeList.add(placesData(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                                resources.getIdentifier(cursor.getString(4), "drawable", this.packageName)))
                    }

                    cursor.close()
                    sqlDB.close()
                }

                "hagye" -> {    //하계 검색
                    cursor = sqlDB.rawQuery("SELECT * FROM placesTBL WHERE gArea LIKE 'hagye';", null)
                    while (cursor.moveToNext()) {
                        placeList.add(placesData(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                                resources.getIdentifier(cursor.getString(4), "drawable", this.packageName)))
                    }

                    cursor.close()
                    sqlDB.close()
                }

                "wolgye" -> {    //월계 검색
                    cursor = sqlDB.rawQuery("SELECT * FROM placesTBL WHERE gArea LIKE 'wolgye';", null)
                    while (cursor.moveToNext()) {
                        placeList.add(placesData(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                                resources.getIdentifier(cursor.getString(4), "drawable", this.packageName)))
                    }
                    cursor.close()
                    sqlDB.close()
                }

                "gongneung" -> {    //공릉 검색
                    cursor = sqlDB.rawQuery("SELECT * FROM placesTBL WHERE gArea LIKE 'gongneung';", null)
                    while (cursor.moveToNext()) {
                        placeList.add(placesData(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                                resources.getIdentifier(cursor.getString(4), "drawable", this.packageName)))
                    }

                    cursor.close()
                    sqlDB.close()
                }
            }

        } else {        //식당 검색
            when(search_area) {
                "nowon" -> {    //노원구 전체 검색
                    cursor = sqlDB.rawQuery("SELECT * FROM restaurantTBL;", null)

                    while (cursor.moveToNext()) {
                        placeList.add(placesData(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                                resources.getIdentifier(cursor.getString(4), "drawable", this.packageName)))
                    }

                    cursor.close()
                    sqlDB.close()
                }

                "sanggye" -> {    //상계 검색
                    cursor = sqlDB.rawQuery("SELECT * FROM restaurantTBL WHERE gArea LIKE 'sanggye';", null)
                    while (cursor.moveToNext()) {
                        placeList.add(placesData(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                                resources.getIdentifier(cursor.getString(4), "drawable", this.packageName)))
                    }

                    cursor.close()
                    sqlDB.close()
                }

                "junggye" -> {    //중계 검색
                    cursor = sqlDB.rawQuery("SELECT * FROM restaurantTBL WHERE gArea LIKE 'junggye';", null)
                    while (cursor.moveToNext()) {
                        placeList.add(placesData(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                                resources.getIdentifier(cursor.getString(4), "drawable", this.packageName)))
                    }

                    cursor.close()
                    sqlDB.close()
                }

                "hagye" -> {    //하계 검색
                    cursor = sqlDB.rawQuery("SELECT * FROM restaurantTBL WHERE gArea LIKE 'hagye';", null)
                    while (cursor.moveToNext()) {
                        placeList.add(placesData(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                                resources.getIdentifier(cursor.getString(4), "drawable", this.packageName)))
                    }

                    cursor.close()
                    sqlDB.close()
                }

                "wolgye" -> {    //월계 검색
                    cursor = sqlDB.rawQuery("SELECT * FROM restaurantTBL WHERE gArea LIKE 'wolgye';", null)
                    while (cursor.moveToNext()) {
                        placeList.add(placesData(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                                resources.getIdentifier(cursor.getString(4), "drawable", this.packageName)))
                    }
                    cursor.close()
                    sqlDB.close()
                }

                "gongneung" -> {    //공릉 검색
                    cursor = sqlDB.rawQuery("SELECT * FROM restaurantTBL WHERE gArea LIKE 'gongneung';", null)
                    while (cursor.moveToNext()) {
                        placeList.add(placesData(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                                resources.getIdentifier(cursor.getString(4), "drawable", this.packageName)))
                    }

                    cursor.close()
                    sqlDB.close()
                }
            }
        }

        val adapter = search_recyclerAdapter(placeList)
        search_recyclerView.adapter = adapter
    }

    private fun manageImage() {     //검색 구역 구분 이미지 투명도 조절
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

    private fun print_toast() {
        Toast.makeText(this, "검색 결과가 없습니다.", Toast.LENGTH_LONG).show()
    }
}