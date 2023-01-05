package com.example.musicviewer.view

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicviewer.R
import com.example.musicviewer.databinding.FragmentRockBinding
import com.example.musicviewer.model.MusicResponse
import com.example.musicviewer.model.remote.*
import com.example.musicviewer.view.adapter.MusicAdapter

import retrofit2.*


class RockFragment: Fragment(R.layout.fragment_rock){



    private lateinit var binding: FragmentRockBinding




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
        d("RockFragment", "After initviews")

        return binding.root
    }

        Network().api.getRockMusic(musicTitle,
            musicGroup,
            musicImg,
            musicPrice)
            .enqueue(
                object: Callback<MusicResponse> {
                    override fun onResponse(
                        call: Call<MusicResponse>,
                        response: Response<MusicResponse>
                    ) {
                        if (response.isSuccessful){
                            //data
                        }else{
                            //empty
                            d( "onResponse:", response.message())
                        }
                    }

                    override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                        d("OnResponse", "${t.message}")
                        t.printStackTrace()
                    }

                }
            )
    }

    private fun initViews() {

    }


}//end of class


