package com.example.guru2_team21

import android.content.Context
import android.content.Intent
import android.icu.text.IDNA
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class search_recyclerAdapter(val placeList : ArrayList<placesData>) :
RecyclerView.Adapter<search_recyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_search_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return placeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.placeImage.setImageResource(placeList.get(position).img)
        holder.placeName.text = placeList.get(position).name

        holder.itemView.setOnClickListener {
            Intent(holder.itemView.context, InformationActivity::class.java).apply {
                putExtra("data", placeList.get(position))
                //addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }.run { holder.itemView.context.startActivity(this)}
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val placeName: TextView = itemView.findViewById(R.id.recyclerText)
        val placeImage: ImageView = itemView.findViewById(R.id.recyclerImage)
    }
}
