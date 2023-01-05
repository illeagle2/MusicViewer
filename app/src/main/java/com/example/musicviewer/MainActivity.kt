package com.example.musicviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicviewer.databinding.ActivityMainBinding
import com.example.musicviewer.model.MusicResponse
import com.example.musicviewer.model.remote.BASE_URL
import com.example.musicviewer.view.ClassicFragment
import com.example.musicviewer.view.Communicator
import com.example.musicviewer.view.PopFragment
import com.example.musicviewer.view.RockFragment
import com.example.musicviewer.view.adapter.MusicAdapter
import retrofit2.Retrofit


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var musicAdapter: MusicAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rockFragment = RockFragment()
        val classicFragment = ClassicFragment()
        val popFragment = PopFragment()
        //val retrofit = Retrofit.Builder().baseUrl(BASE_URL)

        setCurrentFragment(rockFragment)


        binding.bottomNavigationView.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.miRock -> setCurrentFragment(rockFragment)
                R.id.miClassic -> setCurrentFragment(classicFragment)
                R.id.miPop -> setCurrentFragment(popFragment)
            }

            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
        replace(R.id.flFragment, fragment)
        commit()
    }


}