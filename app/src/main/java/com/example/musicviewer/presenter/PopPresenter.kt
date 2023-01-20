package com.example.musicviewer.presenter

import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.musicviewer.model.MusicResponse
import com.example.musicviewer.model.remote.MusicService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PopPresenter @Inject constructor(
    private val serviceAPI: MusicService
): PopPresenterContract {

    private var viewContract: PopViewContract? = null

    override fun initialisePresenter(viewContract: PopViewContract) {
        this.viewContract = viewContract
    }

    override fun destroyPresenter() {
        viewContract = null
    }


    override fun getPopMusic(lifecycleCoroutineScope: LifecycleCoroutineScope) {

        viewContract?.loading(true)

        lifecycleCoroutineScope.launchWhenCreated {
            val response = try {
                serviceAPI.getPopMusic()
            } catch (e: IOException){
                Log.e("PopPresenter", "Missing internet connection")
                return@launchWhenCreated
            } catch (e: HttpException){
                Log.e("PopPresenter", "unexpected response")
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null){
                //good response
                viewContract?.success(response.body()!!)

            } else{
                Log.e("PopPresenter", "Response not successful")
            }
        }
    }
}

interface PopViewContract {
    fun loading (isLoading: Boolean)
    fun error (e: Exception)
    fun success(musicResponse: MusicResponse)
}

interface PopPresenterContract{
    fun initialisePresenter(viewContract: PopViewContract)
    fun destroyPresenter()
    fun getPopMusic(lifecycleCoroutineScope: LifecycleCoroutineScope)
}