package com.example.musicviewer.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicviewer.databinding.MusicItemBinding
import com.example.musicviewer.model.MusicResponse
import com.example.musicviewer.model.Song
import com.squareup.picasso.Picasso

class MusicAdapter(
    private var dataSet: MutableList<MusicResponse>
): RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {


     class MusicViewHolder(private val binding: MusicItemBinding):
        RecyclerView.ViewHolder(binding.root){

            fun onBind(musicItem: Song){
                binding.apply {
                    tvTitle.text = musicItem.artistName
                    tvGroup.text = musicItem.collectionName
                    tvPrice.text = musicItem.trackPrice.toString()
                    //Picasso
                    //load img for music cover
                    Picasso.get()
                        .load(musicItem.artworkUrl60).into(ivArtwork)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    = MusicViewHolder(
        MusicItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.onBind(
            dataSet[position].results[position]
        )
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }


        fun updateDataSet(items: MusicResponse){
            val originalSize = dataSet.size - 1
            //dataSet.addAll(items)

        notifyItemRangeInserted(originalSize, 10)
    }
}