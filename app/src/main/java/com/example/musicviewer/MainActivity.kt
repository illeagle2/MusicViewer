package com.example.musicviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicviewer.databinding.ActivityMainBinding
import com.example.musicviewer.view.ClassicFragment
import com.example.musicviewer.view.PopFragment
import com.example.musicviewer.view.RockFragment
import com.example.musicviewer.view.Todo
import com.example.musicviewer.view.adapter.TodoAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rockList: RecyclerView
    private lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rockFragment = RockFragment()
        val classicFragment = ClassicFragment()
        val popFragment = PopFragment()
        var todoList = mutableListOf(
            Todo("bobs song"),
            Todo("marrys song"),
            Todo("James song")
        )

        adapter = TodoAdapter(todoList)
        rockList.adapter = adapter
        rockList.layoutManager = LinearLayoutManager(this)

        //rockList = findViewById(R.id.miRock)

        //adapter = TodoAdapter(todoList)

        //rockList.adapter = adapter
        //rockList.layoutManager =

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