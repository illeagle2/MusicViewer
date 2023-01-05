package com.example.musicviewer.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.musicviewer.databinding.MusicItemBinding
import com.example.musicviewer.model.MusicResponse
import com.example.musicviewer.model.Song
import com.squareup.picasso.Picasso

class MusicAdapter(
    //private var dataSet: MutableList<MusicResponse>
): RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {


     inner class MusicViewHolder(val binding: MusicItemBinding):
        RecyclerView.ViewHolder(binding.root)

         private val diffCallback = object : DiffUtil.ItemCallback<Song>() {
             override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
                 return oldItem.artistId == newItem.artistId
             }

             override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
                 return oldItem == newItem
             }
         }

         private val differ = AsyncListDiffer(this@MusicAdapter, diffCallback)
         var songs: List<Song>
             get() = differ.currentList
             set(value) {
                 differ.submitList(value)
             }


//            fun onBind(musicItem: Song){
//                binding.apply {
//                    tvTitle.text = musicItem.artistName
//                    tvGroup.text = musicItem.collectionName
//                    tvPrice.text = musicItem.trackPrice.toString()
//                    //Picasso
//                    //load img for music cover
//                    Picasso.get()
//                        .load(musicItem.artworkUrl60).into(ivArtwork)
//                }
//            }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicAdapter.MusicViewHolder {
        return MusicViewHolder(
            MusicItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: MusicAdapter.MusicViewHolder, position: Int) {
        holder.binding.apply {
            val song = songs[position]
            tvTitle.text = song.artistName
            tvGroup.text = song.collectionName
            tvPrice.text = song.trackPrice.toString()
            Picasso.get().load(song.artworkUrl60).into(ivArtwork)
        }
    }

    override fun getItemCount(): Int {
        return songs.size
    }
}