package com.example.guru2_team21

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
class myDBHelper(context : Context) : SQLiteOpenHelper(context, "NowonDB.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE IF NOT EXISTS placesTBL (gArea CHAR(20) PRIMARY KEY, gName CHAR(20), gAddress STRING, gInformation STRING, gImages STRING);")
        //db!!.execSQL("CREATE TABLE IF NOT EXISTS restaurantTBL (gArea CHAR(20) PRIMARY KEY, gName CHAR(20), gAddress STRING, gInformation STRING, gImages STRING);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS placesTBL")
        //db!!.execSQL("DROP TABLE IF EXISTS restaurantTBL")
        onCreate(db)
    }
}