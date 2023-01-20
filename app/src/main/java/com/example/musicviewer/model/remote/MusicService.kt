package com.example.musicviewer.model.remote

import com.example.musicviewer.model.MusicResponse
import com.example.musicviewer.model.Song
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicService {

//https://itunes.apple.com/search?term=classick&amp;media=music&amp;entity=song&amp;limit=50

    @GET(ENDPOINT)
    suspend fun getRockMusic(
        @Query(PARAM_TERM) musicGenre: String = "rock",
        @Query(PARAM_MEDIA) musicMedia: String = "music",
        @Query(PARAM_ENTITY) musicSong: String = "song",
        @Query(PARAM_LIMIT) musicLimit: Double = 50.00
    ): Response<MusicResponse>

    @GET(ENDPOINT)
    suspend fun getPopMusic(
        @Query(PARAM_TERM) musicGenre: String = "pop",
        @Query(PARAM_MEDIA) musicMedia: String = "music",
        @Query(PARAM_ENTITY) musicSong: String = "song",
        @Query(PARAM_LIMIT) musicLimit: Double = 50.00
    ): Response<MusicResponse>

    @GET(ENDPOINT)
    suspend fun getClassicMusic(
        @Query(PARAM_TERM) musicGenre: String = "classik",
        @Query(PARAM_MEDIA) musicMedia: String = "music",
        @Query(PARAM_ENTITY) musicSong: String = "song",
        @Query(PARAM_LIMIT) musicLimit: Double = 50.00
    ): Response<MusicResponse>

}