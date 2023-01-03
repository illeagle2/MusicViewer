package com.example.musicviewer.view.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicviewer.R

class TodoViewHolder (private val view: View): RecyclerView.ViewHolder(view){
    val musicTitle: TextView = view.findViewById(R.id.tvTitle)

}