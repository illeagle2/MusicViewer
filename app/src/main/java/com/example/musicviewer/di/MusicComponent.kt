package com.example.musicviewer.di

import com.example.musicviewer.MainActivity
import com.example.musicviewer.view.ClassicFragment
import com.example.musicviewer.view.PopFragment
import com.example.musicviewer.view.RockFragment
import dagger.Component


@Component(modules = [
    NetworkModule::class,
    AppModule::class,
    PresentersModule::class
])
interface MusicComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(rockFragment: RockFragment)
    fun inject(popFragment: PopFragment)
    fun inject(classicFragment: ClassicFragment)
}