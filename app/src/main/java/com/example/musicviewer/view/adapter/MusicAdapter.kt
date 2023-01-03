package com.example.musicviewer.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicviewer.R
import com.example.musicviewer.model.MusicResponse

class MusicAdapter(
    private var dataSet: List<MusicResponse>
): RecyclerView.Adapter<TodoViewHolder>() {

    fun updateDataSet(newDataSet: List<MusicResponse>){
        dataSet = newDataSet
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.musicTitle.text = todos[position].title
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}