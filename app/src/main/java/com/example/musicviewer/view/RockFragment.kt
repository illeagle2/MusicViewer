package com.example.musicviewer.view

import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicviewer.R
import com.example.musicviewer.databinding.FragmentRockBinding
import com.example.musicviewer.model.MusicResponse
import com.example.musicviewer.model.remote.*
import com.example.musicviewer.presenter.RockViewContract
import com.example.musicviewer.view.adapter.MusicAdapter

import retrofit2.*
import java.io.IOException


class RockFragment: Fragment(R.layout.fragment_rock), RockViewContract {//end of class

    private lateinit var binding: FragmentRockBinding
    private lateinit var musicAdapter: MusicAdapter

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
        d("RockFragment", "After initviews")

        return binding.root
    }


    private fun initViews() {
        musicAdapter = MusicAdapter()
        binding.rvRockSongs.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = musicAdapter
        }
    }

    private fun getData(){

        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getRockMusic()
            } catch (e: IOException){
                Log.e("RockFragment", "Missing internet connection")
                return@launchWhenCreated
            } catch (e: HttpException){
                Log.e("RockFragment", "unexpected response")
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null){
                //good response
                musicAdapter.songs = response.body()!!.results
            } else{
                Log.e("RockFragment", "Response not successful")
            }
        }
    }

    override fun loading(isLoading: Boolean) {
        Toast.makeText(requireContext(), "loading music...", Toast.LENGTH_LONG).show()
    }

    override fun error(e: Exception) {
        TODO("Not yet implemented")
    }

    override fun success(musicResponse: MusicResponse) {
        musicAdapter = MusicAdapter()
    }

    override fun displayWarningMessage(message: String) {
        TODO("Not yet implemented")
    }


}


