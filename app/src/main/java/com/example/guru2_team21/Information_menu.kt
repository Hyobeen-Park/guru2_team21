package com.example.guru2_team21

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Information_menu(context: Context) {
    private val dlg = Dialog(context)

    lateinit var add_to_route: TextView
    private lateinit var onClickListener: OnDialogClickListener

    fun setOnClickListener(listener: OnDialogClickListener) {
        onClickListener = listener
    }

    fun start() {
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dlg.setContentView(R.layout.activity_information_menu)
        dlg.setCancelable(true)

        add_to_route = dlg.findViewById(R.id.add_to_route)

        add_to_route.setOnClickListener {
            //나만의 루트에 추가하기
            dlg.dismiss()
        }

        dlg.show()
    }

    interface OnDialogClickListener {
        fun onClicked(name: String)
    }
}