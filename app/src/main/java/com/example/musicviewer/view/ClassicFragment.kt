package com.example.musicviewer.view

import android.os.Bundle
import android.util.Log
import android.util.Log.e
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicviewer.R
import com.example.musicviewer.databinding.FragmentClassicBinding
import com.example.musicviewer.databinding.FragmentRockBinding
import com.example.musicviewer.model.MusicResponse
import com.example.musicviewer.model.remote.RetrofitInstance
import com.example.musicviewer.view.adapter.MusicAdapter
import retrofit2.HttpException
import java.io.IOException

class ClassicFragment: Fragment(R.layout.fragment_classic) {

    private lateinit var binding: FragmentClassicBinding
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
        musicAdapter = MusicAdapter()
        binding.rvClassicSongs.adapter = musicAdapter
        binding.rvClassicSongs.layoutManager = LinearLayoutManager(context)

    }

    private fun getData(){
//        val classicList = listOf(MusicResponse("first classic song"), MusicResponse("second classic song"))
//        adapter.updateDataSet(classicList)
        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getClassicMusic()
            } catch (e: IOException){
                Log.e("ClassicFragment", "Missing internet connection")
                return@launchWhenCreated
            } catch (e: HttpException){
                Log.e("ClassicFragment", "unexpected response")
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null){
                //good response
                musicAdapter.songs = response.body()!!.results
            } else{
                Log.e("ClassisFragment", "Response not successful")
            }
        }


    }
}