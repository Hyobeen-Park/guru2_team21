package com.example.guru2_team21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.text.DateFormat
import java.util.*

class RecordActivity : AppCompatActivity() {

    lateinit var calendarView: CalendarView
    lateinit var datetv: TextView
    lateinit var diaryedt: EditText
    lateinit var diaryimg: ImageView
    lateinit var writebtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        calendarView = findViewById<CalendarView>(R.id.calenderView)
        datetv = findViewById<TextView>(R.id.datetv)
        diaryedt = findViewById<EditText>(R.id.diaryedt)
        diaryimg = findViewById<ImageView>(R.id.diaryimg)
        writebtn = findViewById<Button>(R.id.writebtn)

        val currentDate: String = DateFormat.getDateInstance().format(Date())
        datetv.setText(currentDate)

        calendarView.setOnDateChangeListener { calendarView, year, month, date ->
            datetv.text = "$year"+". "+"${month+1}"+". "+"$date"+"."
        }

        writebtn.setOnClickListener {
            Toast.makeText(this, "작성이 완료되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}