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

    lateinit var myHelper: myDBHelper
    lateinit var information : placesData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        place_name = findViewById(R.id.place_name)
        place_image = findViewById(R.id.place_image)
        place_address = findViewById(R.id.place_address)
        place_information = findViewById(R.id.place_information)

        myHelper = myDBHelper(this)
        information = intent.getSerializableExtra("data") as placesData     //intent에서 데이터 placesData로 받아오기

        setTitle(information.name)
        print_info()
    }

    private fun print_info() {      //정보 출력
        place_name.setText(information.name)
        place_image.setImageResource(resources.getIdentifier(information.img.toString(), "drawable", this.packageName))
        place_address.append(information.address)
        place_information.append("\n" + information.information)
    }
}