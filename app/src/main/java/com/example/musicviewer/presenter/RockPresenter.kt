package com.example.musicviewer.presenter

import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.musicviewer.model.MusicResponse
import com.example.musicviewer.model.remote.MusicService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RockPresenter @Inject constructor(
    private val serviceAPI: MusicService
): RockPresenterContract {

    private var viewContract: RockViewContract? = null

    override fun initialisePresenter(viewContract: RockViewContract) {
        this.viewContract = viewContract
    }

    override fun destroyPresenter() {
        viewContract = null
    }


    override fun getRockMusic(lifecycleCoroutineScope: LifecycleCoroutineScope) {

        viewContract?.loading(true)

        lifecycleCoroutineScope.launchWhenCreated {
            val response = try {
                serviceAPI.getRockMusic()
            } catch (e: IOException){
                Log.e("RockPresenter", "Missing internet connection")
                return@launchWhenCreated
            } catch (e: HttpException){
                Log.e("RockPresenter", "unexpected response")
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null){
                //good response
                viewContract?.success(response.body()!!)

            } else{
                Log.e("RockPresenter", "Response not successful")
            }
        }
    }
}

interface RockViewContract {
    fun loading (isLoading: Boolean)
    fun error (e: Exception)
    fun success(musicResponse: MusicResponse)
}

interface RockPresenterContract{
    fun initialisePresenter(viewContract: RockViewContract)
    fun destroyPresenter()
    fun getRockMusic(lifecycleCoroutineScope: LifecycleCoroutineScope)
}