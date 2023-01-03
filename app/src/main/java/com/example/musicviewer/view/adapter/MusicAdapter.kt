package com.example.musicviewer.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicviewer.R
import com.example.musicviewer.databinding.MusicItemBinding
import com.example.musicviewer.model.MusicResponse

class MusicAdapter(
    private var dataSet: List<MusicResponse>
): RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {

    fun updateDataSet(newDataSet: List<MusicResponse>){
        dataSet = newDataSet
        notifyDataSetChanged()
    }

    class MusicViewHolder(private val binding: MusicItemBinding):
        RecyclerView.ViewHolder(binding.root){

            fun onBind(musicItem: MusicResponse){
                binding.apply {
                    tvTitle.text = musicItem.title
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        return MusicViewHolder(
            MusicItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.onBind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}