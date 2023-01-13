package com.example.musicviewer.view

import android.app.AlertDialog
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
import com.example.musicviewer.presenter.RockPresenter
import com.example.musicviewer.presenter.RockPresenterContract
import com.example.musicviewer.presenter.RockViewContract
import com.example.musicviewer.view.adapter.MusicAdapter

import retrofit2.*
import java.io.IOException


class RockFragment: Fragment(R.layout.fragment_rock), RockViewContract {

    private lateinit var binding: FragmentRockBinding
    private lateinit var musicAdapter: MusicAdapter
    private val presenter: RockPresenterContract by lazy {
        RockPresenter()
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

        presenter.initialisePresenter(this)
        presenter.getRockMusic(lifecycleScope)

        initViews()

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



    override fun loading(isLoading: Boolean) {
        Toast.makeText(requireContext(), "loading music...", Toast.LENGTH_LONG).show()
    }

    override fun error(e: Exception) {
        AlertDialog.Builder(requireActivity())
            .setTitle("Error has occurred")
            .setMessage("${e.message}")
            .setPositiveButton("OK") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .setNegativeButton("CANCEL") {dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .create()
            .show()
    }

    override fun success(musicResponse: MusicResponse) {
        musicAdapter.songs = musicResponse.results
    }

    override fun displayWarningMessage(message: String) {
        TODO("Not yet implemented")
    }


}//end of class


