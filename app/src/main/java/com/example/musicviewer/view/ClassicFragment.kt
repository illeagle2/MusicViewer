package com.example.musicviewer.view

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicviewer.R
import com.example.musicviewer.databinding.FragmentClassicBinding
import com.example.musicviewer.di.MusicViewerApp
import com.example.musicviewer.model.MusicResponse
import com.example.musicviewer.presenter.ClassicPresenterContract
import com.example.musicviewer.presenter.ClassicViewContract
import com.example.musicviewer.view.adapter.MusicAdapter
import javax.inject.Inject

class ClassicFragment: Fragment(R.layout.fragment_classic), ClassicViewContract {

    private lateinit var binding: FragmentClassicBinding
    private lateinit var musicAdapter: MusicAdapter

    @Inject
    lateinit var presenter: ClassicPresenterContract

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MusicViewerApp.musicComponent.inject(this)
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

        presenter.initialisePresenter(this)
        presenter.getClassicMusic(lifecycleScope)

        initViews()

        return binding.root
    }



    private fun initViews() {
        musicAdapter = MusicAdapter()
        binding.rvClassicSongs.apply {
            adapter = musicAdapter
            layoutManager = LinearLayoutManager(context)
        }
        binding.swipeContainer3.setOnRefreshListener {
            Toast.makeText(requireContext(), "REFRESH", Toast.LENGTH_SHORT).show()
            musicAdapter.songs = emptyList()
            presenter.getClassicMusic(lifecycleScope)
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
        if (binding.swipeContainer3.isRefreshing){
            binding.swipeContainer3.isRefreshing = false
        }
    }



}