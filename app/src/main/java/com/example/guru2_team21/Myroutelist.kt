package com.example.guru2_team21

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class Myroutelist : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myroutelist)
        setTitle("나만의 코스 목록 확인");
        change_fragment1()      //리스트1을 처음으로 보여주기

        val Intent: Intent = getIntent();
        val list_str_check = Intent.getStringExtra("list_check");

        if(list_str_check.equals("1")){


        }else if(list_str_check.equals("2")){

        }else if(list_str_check.equals("3")){

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu);
        menuInflater.inflate(R.menu.list_menu, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.go_list1 -> change_fragment1()
            R.id.go_list2 -> change_fragment2()
            R.id.go_list3 -> change_fragment3()
        }
        return super.onOptionsItemSelected(item)
    }

    fun change_fragment1(){
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = Fragment_list1()
        transaction.replace(R.id.list_frame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun change_fragment2(){
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = Fragment_list2()
        transaction.replace(R.id.list_frame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun change_fragment3(){
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = Fragment_list3()
        transaction.replace(R.id.list_frame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}