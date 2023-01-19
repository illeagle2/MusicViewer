package com.example.musicviewer.view

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicviewer.R
import com.example.musicviewer.databinding.FragmentClassicBinding
import com.example.musicviewer.databinding.FragmentPopBinding
import com.example.musicviewer.di.MusicViewerApp
import com.example.musicviewer.model.MusicResponse
import com.example.musicviewer.model.remote.RetrofitInstance
import com.example.musicviewer.presenter.PopPresenter
import com.example.musicviewer.presenter.PopPresenterContract
import com.example.musicviewer.presenter.PopViewContract
import com.example.musicviewer.view.adapter.MusicAdapter
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PopFragment: Fragment(R.layout.fragment_pop), PopViewContract {

    private lateinit var binding: FragmentPopBinding
    private lateinit var musicAdapter: MusicAdapter

    @Inject
    lateinit var presenter: PopPresenterContract

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

        binding = FragmentPopBinding.inflate(
            inflater,
            container,
            false
        )

        presenter.initialisePresenter(this)
        presenter.getPopMusic(lifecycleScope)

        initViews()

        return binding.root
    }

    private fun initViews() {
        musicAdapter = MusicAdapter()
        binding.rvPopSongs.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = musicAdapter
        }
        binding.swipeContainer2.setOnRefreshListener {
            Toast.makeText(requireContext(), "REFRESH", Toast.LENGTH_SHORT).show()
            //myUpdateOperation()
            musicAdapter.songs = emptyList()
            presenter.getPopMusic(lifecycleScope)
            //binding.swipeContainer2.isRefreshing = false
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
        if (binding.swipeContainer2.isRefreshing){
            binding.swipeContainer2.isRefreshing = false
        }
    }

    override fun displayWarningMessage(message: String) {

    }
}