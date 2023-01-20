package com.example.musicviewer.di

import android.app.Application

class MusicViewerApp: Application() {


    override fun onCreate() {
        super.onCreate()
        //add dagger
        musicComponent = DaggerMusicComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        lateinit var musicComponent: MusicComponent
    }
}