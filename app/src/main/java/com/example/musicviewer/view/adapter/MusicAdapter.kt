package com.example.musicviewer.view.adapter

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.musicviewer.databinding.MusicItemBinding
import com.example.musicviewer.model.MusicResponse
import com.example.musicviewer.model.Song
import com.example.musicviewer.view.ClassicFragment
import com.squareup.picasso.Picasso
import kotlinx.coroutines.NonDisposableHandle.parent

class MusicAdapter : RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {


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
        val context = holder.itemView.context

        holder.binding.apply {
            val song = songs[position]
            tvTitle.text = song.artistName
            tvGroup.text = song.collectionName
            tvPrice.text = song.trackPrice.toString() + " USD"
            //tvPrice.text = "$text USD"
            Picasso.get().load(song.artworkUrl100).resize(300,300).into(ivArtwork)
        }

        holder.itemView.setOnClickListener{
            val queryUrl: Uri = Uri.parse(songs[position].previewUrl)
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            context.startActivity(intent)
            }

    }


    override fun getItemCount(): Int {
        return songs.size
    }
}