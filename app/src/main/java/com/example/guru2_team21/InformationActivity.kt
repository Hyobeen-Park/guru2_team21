package com.example.guru2_team21

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text


class InformationActivity : AppCompatActivity() {
    lateinit var place_name: TextView
    lateinit var place_image: ImageView
    lateinit var place_address: TextView
    lateinit var place_information: TextView
    lateinit var info_menu: ImageView

    lateinit var myHelper: myDBHelper
    lateinit var sqlDB: SQLiteDatabase

    lateinit var name : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        place_name = findViewById(R.id.place_name)
        place_image = findViewById(R.id.place_image)
        place_address = findViewById(R.id.place_address)
        place_information = findViewById(R.id.place_information)
        info_menu = findViewById(R.id.info_menu)

        myHelper = myDBHelper(this)
        name = intent.getStringExtra("name").toString()
        setTitle(name)

        print_info()

        info_menu.setOnClickListener {
            //dialog --> 코스에 추가하기
        }
    }

    private fun print_info() {
        sqlDB = myHelper.readableDatabase
        var cursor: Cursor

        cursor = sqlDB.rawQuery("SELECT * FROM placesTBL", null)

        place_name.setText(name)
        //place_image.setImageResource(R.drawable.(cursor.getString(4))) --> 수정
        //place_address.setText(cursor.getString(2))
        //place_information.setText(cursor.getString(3))

        cursor.close()
        sqlDB.close()
    }
}