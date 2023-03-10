package com.example.musicviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.musicviewer.databinding.ActivityMainBinding
import com.example.musicviewer.di.MusicViewerApp
import com.example.musicviewer.view.ClassicFragment
import com.example.musicviewer.view.PopFragment
import com.example.musicviewer.view.RockFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MusicViewerApp.musicComponent.inject(this)


        val rockFragment = RockFragment()
        val classicFragment = ClassicFragment()
        val popFragment = PopFragment()




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