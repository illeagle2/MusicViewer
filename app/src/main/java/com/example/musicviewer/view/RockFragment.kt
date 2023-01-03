package com.example.musicviewer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicviewer.R
import com.example.musicviewer.databinding.FragmentRockBinding
import com.example.musicviewer.databinding.MusicItemBinding
import com.example.musicviewer.model.MusicResponse
import com.example.musicviewer.view.adapter.MusicAdapter

class RockFragment: Fragment(R.layout.fragment_rock) {

    private lateinit var binding: FragmentRockBinding
    private val adapter: MusicAdapter by lazy{
        MusicAdapter(emptyList())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentRockBinding.inflate(
            inflater,
            container,
            false
        )

        initViews()
        getData()
        return binding.root
    }

    private fun initViews() {
        binding.rvRockSongs.layoutManager = LinearLayoutManager(context)
        binding.rvRockSongs.adapter = adapter
    }

    private fun getData(){
        val rockList = listOf(MusicResponse("first rock song"),
            MusicResponse("second rock song"),
            MusicResponse("3 rock song"),
            MusicResponse("4 rock song"),
            MusicResponse("5 rock song"),
            MusicResponse("6 rock song"),
            MusicResponse("7 rock song"),
            MusicResponse("8 rock song")
            )
       adapter.updateDataSet(rockList)

    }
}