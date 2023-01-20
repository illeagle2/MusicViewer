package com.example.musicviewer.presenter

import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.musicviewer.model.MusicResponse
import com.example.musicviewer.model.remote.MusicService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ClassicPresenter @Inject constructor(
    private val serviceAPI: MusicService
): ClassicPresenterContract {

    private var viewContract: ClassicViewContract? = null

    override fun initialisePresenter(viewContract: ClassicViewContract) {
        this.viewContract = viewContract
    }

    override fun destroyPresenter() {
        viewContract = null
    }


    override fun getClassicMusic(lifecycleCoroutineScope: LifecycleCoroutineScope) {

        viewContract?.loading(true)

        lifecycleCoroutineScope.launchWhenCreated {
            val response = try {
                serviceAPI.getClassicMusic()
            } catch (e: IOException){
                Log.e("ClassicPresenter", "Missing internet connection")
                return@launchWhenCreated
            } catch (e: HttpException){
                Log.e("ClassicPresenter", "unexpected response")
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null){
                //good response
                viewContract?.success(response.body()!!)

            } else{
                Log.e("ClassicPresenter", "Response not successful")
            }
        }
    }
}

interface ClassicViewContract {
    fun loading (isLoading: Boolean)
    fun error (e: Exception)
    fun success(musicResponse: MusicResponse)
}

interface ClassicPresenterContract{
    fun initialisePresenter(viewContract: ClassicViewContract)
    fun destroyPresenter()
    fun getClassicMusic(lifecycleCoroutineScope: LifecycleCoroutineScope)
}