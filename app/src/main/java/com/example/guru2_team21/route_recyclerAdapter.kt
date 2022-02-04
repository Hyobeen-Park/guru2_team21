package com.example.guru2_team21

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class route_recyclerAdapter (val routelist : ArrayList<routesData>) :
RecyclerView.Adapter<route_recyclerAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): route_recyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_route_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return routelist.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val placeName: TextView = itemView.findViewById(R.id.route_recyclertext)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.placeName.text = routelist.get(position).name
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    interface OnItemClickListener{
        fun onClick(v:View, position: Int)
    }
    private lateinit var itemClickListener : OnItemClickListener

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}