package com.example.musicviewer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicviewer.R
import com.example.musicviewer.databinding.FragmentClassicBinding
import com.example.musicviewer.databinding.FragmentRockBinding
import com.example.musicviewer.model.MusicResponse
import com.example.musicviewer.view.adapter.MusicAdapter

class ClassicFragment: Fragment(R.layout.fragment_classic) {

    private lateinit var binding: FragmentClassicBinding
    private val adapter: MusicAdapter by lazy{
        MusicAdapter(emptyList())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentClassicBinding.inflate(
            inflater,
            container,
            false
        )

        initViews()
        getData()
        return binding.root
    }

    private fun initViews() {
        binding.rvClassicSongs.layoutManager = LinearLayoutManager(context)
        binding.rvClassicSongs.adapter = adapter
    }

    private fun getData(){
        val classicList = listOf(MusicResponse("first classic song"), MusicResponse("second classic song"))
        adapter.updateDataSet(classicList)

    }
}