package com.example.guru2_team21

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception
import java.text.DateFormat
import java.util.*
import java.util.jar.Manifest

class RecordActivity : AppCompatActivity() {

    lateinit var calendarView: CalendarView
    lateinit var datetv: TextView
    lateinit var diaryedt: EditText
    lateinit var diaryimg: ImageView
    lateinit var writebtn: Button

    lateinit var myHelper: myDBHelper
    lateinit var sqlDB: SQLiteDatabase

    private val OPEN_GALLERY = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        calendarView = findViewById<CalendarView>(R.id.calenderView)
        datetv = findViewById<TextView>(R.id.datetv)
        diaryedt = findViewById<EditText>(R.id.diaryedt)
        diaryimg = findViewById<ImageView>(R.id.diaryimg)
        writebtn = findViewById<Button>(R.id.writebtn)

        myHelper = myDBHelper(this)


        // 외부저장소 diary 디렉토리 만들기
//        var strSDpath: String = Environment.getExternalStorageDirectory().getAbsolutePath()
//        var diary: File = File(strSDpath+"/diary")
//        diary.mkdir();
//
//        var path = strSDpath + "/diary"
        // 외부저장소 접근권한요청
//        ActivityCompat.requestPermissions(this,
//            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),99)


        val currentDate: String = DateFormat.getDateInstance().format(Date())
        datetv.setText(currentDate)

        diaryimg.setOnClickListener{ openGallery()}

        calendarView.setOnDateChangeListener { calendarView, year, month, date ->
            val name: String = "$year"+". "+"${month+1}"+". "+"$date"+"."
            datetv.text = name
            //val fileName = path + "/" + name


            writebtn.setOnClickListener {
                // diary디렉토리에 txt파일 넣기기
//                   val ouFs = FileOutputStream(fileName)
//                    val str: String = diaryedt.text.toString()
//                    ouFs.write(str.toByteArray())
//                    ouFs.close()
                val str: String = diaryedt.text.toString()
                //val img = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures" + diaryimg
                sqlDB = myHelper.writableDatabase
                sqlDB.execSQL("INSERT INTO diaryTBL VALUES ('"+ name +"', '" + str + "', '" + diaryimg + "');")
                // 테이블에 이미지 넣는거 구현해야함 아직 못함 "INSERT INTO diaryTBL VALUES ('"+ name +"', '" + str + "', '" + diaryimg + "');"
                sqlDB.close()
                writebtn.isClickable
                Toast.makeText(this, "작성이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }

    }

    inner class myDBHelper(context: Context) : SQLiteOpenHelper(context, "diaryDB", null, 1){
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL("CREATE TABLE diaryTBL (gDate CHAR(10) PRIMARY KEY, gDiary CHAR(500), gImage BLOB)") //gImg BLOB
        }

        override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS diaryTBL")
            onCreate(db)
        }
    }


    private fun openGallery(){

        val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent, OPEN_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            if(requestCode == OPEN_GALLERY){
                var currentImageURI : Uri? = data?.data

                try{
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, currentImageURI)
                    diaryimg.setImageBitmap(bitmap)
                }catch (e:Exception){
                    e.printStackTrace()
                }
            } else{

            }

        }
    }
}