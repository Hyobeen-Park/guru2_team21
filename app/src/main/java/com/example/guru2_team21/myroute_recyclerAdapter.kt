package com.example.guru2_team21

import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

class myroute_recyclerAdapter (val myroutelist : ArrayList<myroutesData>) : Serializable,
RecyclerView.Adapter<myroute_recyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myroute_recyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_myroute_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myroutelist.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val placeName: TextView = itemView.findViewById(R.id.myroute_recyclertext)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.placeName.text = myroutelist.get(position).name
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    //클릭 이벤트
    interface OnItemClickListener{
        fun onClick(v:View, position: Int)
    }
    private lateinit var itemClickListener : OnItemClickListener

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}