package com.example.musicviewer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicviewer.R
import com.example.musicviewer.databinding.FragmentClassicBinding
import com.example.musicviewer.databinding.FragmentPopBinding
import com.example.musicviewer.model.MusicResponse
import com.example.musicviewer.view.adapter.MusicAdapter

class PopFragment: Fragment(R.layout.fragment_pop) {

    private lateinit var binding: FragmentPopBinding
    private lateinit var musicAdapter: MusicAdapter
//    private val adapter: MusicAdapter by lazy{
//        MusicAdapter(emptyList())
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentPopBinding.inflate(
            inflater,
            container,
            false
        )

        initViews()
        getData()
        return binding.root
    }

    private fun initViews() {
        binding.rvPopSongs.layoutManager = LinearLayoutManager(context)
        binding.rvPopSongs.adapter = musicAdapter
    }

    private fun getData(){
//        val classicList = listOf(MusicResponse("first pop song"), MusicResponse("second pop song"))
//        adapter.updateDataSet(classicList)

    }
}