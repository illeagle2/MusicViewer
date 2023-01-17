package com.example.musicviewer.presenter

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import com.example.musicviewer.model.MusicResponse
import com.example.musicviewer.model.remote.MusicService
import com.example.musicviewer.model.remote.RetrofitInstance
import com.example.musicviewer.utils.FailureResponseException
import com.example.musicviewer.utils.NullResponseException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class PopPresenter: PopPresenterContract {

    private var viewContract: PopViewContract? = null

    override fun initialisePresenter(viewContract: PopViewContract) {
        this.viewContract = viewContract
    }

    override fun destroyPresenter() {
        viewContract = null
    }

    override fun checkNetworkConnection() {
        viewContract?.displayWarningMessage("Check network connection")
    }

    override fun getPopMusic(lifecycleCoroutineScope: LifecycleCoroutineScope) {

        viewContract?.loading(true)

        lifecycleCoroutineScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getPopMusic()
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
    fun displayWarningMessage (message: String)
}

interface PopPresenterContract{
    fun initialisePresenter(viewContract: PopViewContract)
    fun destroyPresenter()
    fun getPopMusic(lifecycleCoroutineScope: LifecycleCoroutineScope)
    fun checkNetworkConnection()
}