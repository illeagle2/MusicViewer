package com.example.musicviewer.view

interface Communicator {

    fun sendDataToSearch(artistName: String, collectionName: String, artworkUrl60: String, trackPrice: Double)

}