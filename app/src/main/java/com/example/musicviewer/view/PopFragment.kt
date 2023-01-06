package com.example.musicviewer.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicviewer.R
import com.example.musicviewer.databinding.FragmentClassicBinding
import com.example.musicviewer.databinding.FragmentPopBinding
import com.example.musicviewer.model.MusicResponse
import com.example.musicviewer.model.remote.RetrofitInstance
import com.example.musicviewer.view.adapter.MusicAdapter
import retrofit2.HttpException
import java.io.IOException

class PopFragment: Fragment(R.layout.fragment_pop) {

    private lateinit var binding: FragmentPopBinding
    private lateinit var musicAdapter: MusicAdapter

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
        musicAdapter = MusicAdapter()
        binding.rvPopSongs.layoutManager = LinearLayoutManager(context)
        binding.rvPopSongs.adapter = musicAdapter
    }

    private fun getData(){

        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getPopMusic()
            } catch (e: IOException){
                Log.e("PopFragment", "Missing internet connection")
                return@launchWhenCreated
            } catch (e: HttpException){
                Log.e("PopFragment", "unexpected response")
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null){
                //good response
                musicAdapter.songs = response.body()!!.results
            } else{
                Log.e("PopFragment", "Response not successful")
            }
        }
    }
}