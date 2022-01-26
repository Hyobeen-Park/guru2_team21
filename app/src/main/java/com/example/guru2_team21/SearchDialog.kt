package com.example.guru2_team21

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.Button
import android.widget.EditText

class SearchDialog(context: Context) {
    private val  dlg = Dialog(context)

    lateinit var search_button: Button
    lateinit var searchTextField: EditText

    fun start() {
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dlg.setContentView(R.layout.activity_search_dialog)
        dlg.setCancelable(true)

        search_button.setOnClickListener {
            //searchTextField text 받아와서 검색하기
        }
        dlg.show()
    }
}