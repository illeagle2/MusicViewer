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

class ClassicPresenter: ClassicPresenterContract {

    private var viewContract: ClassicViewContract? = null

    override fun initialisePresenter(viewContract: ClassicViewContract) {
        this.viewContract = viewContract
    }

    override fun destroyPresenter() {
        viewContract = null
    }

    override fun checkNetworkConnection() {
        viewContract?.displayWarningMessage("Check network connection")
    }

    override fun getClassicMusic(lifecycleCoroutineScope: LifecycleCoroutineScope) {

        viewContract?.loading(true)

        lifecycleCoroutineScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getClassicMusic()
            } catch (e: IOException){
                Log.e("RockFragment", "Missing internet connection")
                return@launchWhenCreated
            } catch (e: HttpException){
                Log.e("RockFragment", "unexpected response")
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null){
                //good response
                viewContract?.success(response.body()!!)

            } else{
                Log.e("RockFragment", "Response not successful")
            }
        }
    }
}

interface ClassicViewContract {
    fun loading (isLoading: Boolean)
    fun error (e: Exception)
    fun success(musicResponse: MusicResponse)
    fun displayWarningMessage (message: String)
}

interface ClassicPresenterContract{
    fun initialisePresenter(viewContract: ClassicViewContract)
    fun destroyPresenter()
    fun getClassicMusic(lifecycleCoroutineScope: LifecycleCoroutineScope)
    fun checkNetworkConnection()
}