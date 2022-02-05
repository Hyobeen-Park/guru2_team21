package com.example.guru2_team21

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

class myroute_check_recyclerAdapter (val myroutechecklist : ArrayList<myroutesCheckData>) :
        RecyclerView.Adapter<myroute_check_recyclerAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myroute_check_recyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_myroute_check_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myroutechecklist.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val placeName: TextView = itemView.findViewById(R.id.myroute_check_recyclertext)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.placeName.text = myroutechecklist.get(position).name
        holder.itemView.setOnClickListener {

        }
    }
}