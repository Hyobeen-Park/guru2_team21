package com.example.guru2_team21

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.*
import java.util.*

class RecordActivity : AppCompatActivity() {

    val TAG: String = "로그"

    // 변수 선언
    lateinit var calendarView: CalendarView
    lateinit var date: TextView
    lateinit var img: ImageView
    lateinit var edt: EditText
    lateinit var btnwrite: Button
    lateinit var list_date: TextView
    lateinit var list_edt: EditText
    lateinit var linearLayout: LinearLayout


    lateinit var myHelper: myDBHelper       // myDBHelper 클래스 변수
    lateinit var sqlDB: SQLiteDatabase      // SQLiteDatabase 클래스 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        Log.d(TAG, "onCreate()")

        // 변수에 id값 대입
        calendarView = findViewById<CalendarView>(R.id.calenderView)
        date = findViewById<TextView>(R.id.datetv)
        img = findViewById<ImageView>(R.id.diaryimg)
        edt = findViewById<EditText>(R.id.diaryedt)
        btnwrite = findViewById<Button>(R.id.btnwrite)
        linearLayout = findViewById<LinearLayout>(R.id.linearlayout_list)


        myHelper = myDBHelper(this)     // myDBHelper클래스로 객체 생성

        myDB()      // sqlite에 저장된 테이블이 보여질 수 있도록 myDB() 함수 호출

        date.setText("날짜를 선택해주세요")

        // 현재 날짜 받아옴
        var cal: Calendar = Calendar.getInstance()
        var y: Int = cal.get(Calendar.YEAR)
        var m: Int = cal.get(Calendar.MONTH)
        var d: Int = cal.get(Calendar.DAY_OF_MONTH)



        // 날짜 선택하기
        calendarView.init(y, m, d, calendarView.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
            Log.d(TAG, "캘린더뷰 setonDateChangeListener()")

            // DB에 저장될 date, 화면에 보여지는 날짜에 넣을 변수
            var day: String = "$year" + ". " + "${month + 1}" + ". " + "$dayOfMonth" + "."
            date.text = day


            // 작성버튼이 클리되었을 때
            btnwrite.setOnClickListener {
                Log.d(TAG, "작성버튼 클릭")

                if (edt.text.isEmpty()) {      // EditText에 내용이 비어있을 때
                    Toast.makeText(this, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
                else {    // EditText에 내용을 입력했을 때
                    val str: String = edt.text.toString()       // EditText에 입력된 값을 문자열로 바꾸어줌

                    sqlDB = myHelper.writableDatabase           // DB를 쓰기모드로 열고 날짜와 EditText에 입력된 값을 CHAR형으로 넣어줌
                    sqlDB.execSQL("INSERT INTO recordTBL VALUES ('" + day + "', '" + str + "');")
                    sqlDB.close()

                    edt.setText("")         // DB에 값이 저장되면 다시 작성할 수 있게 원래 상태로 되돌려 놓음
                    Toast.makeText(this, "작성이 완료되었습니다.", Toast.LENGTH_SHORT).show()

                    myDB()      // 작성된 것을 바로 보여줄 수 있도록 myDB() 함수 실행

                }
            }


        })


    }

    private fun CalendarView.init(y: Int, m: Int, d: Int, onDateChangeListener: Unit) {

    }

    // DB 사용할 수 있도록 myDBHelper 클래스 생성
    inner class myDBHelper(context: Context) : SQLiteOpenHelper(context, "recordDB", null, 1) {
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL("CREATE TABLE recordTBL (date CHAR, diary CHAR)") // recordTBL에 date와 diary column 추가
        }

        override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS recordTBL")          // recordTBL이 이미 있을 시 삭제하고 실행
            onCreate(db)
        }
    }


    @SuppressLint("Range")
    private fun myDB() {    // 이제까지 작성한 기록들을 보여주는 함수

        linearLayout.removeAllViews()

        sqlDB = myHelper.readableDatabase       // DB를 읽기모드로 엶

        var cursor: Cursor
        cursor = sqlDB.rawQuery("SELECT * FROM recordTBL", null)      // rawQuery를 통해 recordTBL에 있는 값들을 가져옴

        var num: Int = 0    // 레이아웃 아이템의 아이디를 주기 위한 변수
        while (cursor.moveToNext()) {
            Log.d(TAG, "cusor.moveToNext()시작")

            var str_date = cursor.getString(cursor.getColumnIndex("date")).toString()   // 현재 cursor에 있는 값을 변수로 받아옴
            var str_diary = cursor.getString(cursor.getColumnIndex("diary")).toString()


            var layout_item: LinearLayout = LinearLayout(this)      // 레이아웃 아이템들을 수직방향으로 생성
            layout_item.orientation = LinearLayout.VERTICAL
            layout_item.id = num

            Log.d(TAG, "item넣기")

            list_date = TextView(this)      // 날짜를 입력받을 TextView
            list_date.text = str_date
            list_date.textSize = 20f
            list_date.setBackgroundColor(Color.parseColor("#ffffff"))
            layout_item.addView(list_date)

            Log.d(TAG, "date 넣기 완료")


            list_edt = EditText(this)       // 기록한 부분을 받는 EditText
            list_edt.setText(str_diary)
            list_edt.textSize = 20f
            list_edt.isFocusable = false
            layout_item.addView(list_edt)

            Log.d(TAG, "diary 넣기 완료")


            var btndelete: Button = Button(this)        // 삭제버튼 아이템 추가
            btndelete.setText("삭제")
            layout_item.addView(btndelete)

            btndelete.setOnClickListener {      // 삭제 버튼 누를 시
                sqlDB = myHelper.readableDatabase
                sqlDB.execSQL("DELETE FROM recordTBL WHERE date = '" + str_date + "';")
                sqlDB.close()
                Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_SHORT).show()
                myDB()
            }

            linearLayout.addView(layout_item)
            num++;
        }


        cursor.close()
        sqlDB.close()

    }
}

private fun Editable.isEmpty(): Boolean {
    return false
}

