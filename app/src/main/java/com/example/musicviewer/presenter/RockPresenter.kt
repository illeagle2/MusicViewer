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

class RockPresenter: RockPresenterContract {

    private var viewContract: RockViewContract? = null

    override fun initialisePresenter(viewContract: RockViewContract) {
        this.viewContract = viewContract
    }

    override fun destroyPresenter() {
        viewContract = null
    }

    override fun checkNetworkConnection() {
        viewContract?.displayWarningMessage("Check network connection")
    }

    override fun getRockMusic(lifecycleCoroutineScope: LifecycleCoroutineScope) {

        viewContract?.loading(true)

        lifecycleCoroutineScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getRockMusic()
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
                //musicAdapter.songs = response.body()!!.results
            } else{
                Log.e("RockFragment", "Response not successful")
            }
        }

//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val response = RetrofitInstance.api.getRockMusic("rock")
//                if (response.isSuccessful) {
//                    response.body()?.let {
//                        withContext(Dispatchers.Main) {
//                            viewContract?.success(it)
//                        }
//                    } ?: throw NullResponseException("Rock music response is null")
//                } else {
//                    throw FailureResponseException(response.errorBody()?.string())
//                }
//            } catch (e: Exception) {
//                Log.d(ContentValues.TAG, "onResponse: ${e.message}")
//                withContext(Dispatchers.Main) {
//                    viewContract?.error(e)
//                }
//            }
//        }
    }
}

interface RockViewContract {
    fun loading (isLoading: Boolean)
    fun error (e: Exception)
    fun success(musicResponse: MusicResponse)
    fun displayWarningMessage (message: String)
}

interface RockPresenterContract{
    fun initialisePresenter(viewContract: RockViewContract)
    fun destroyPresenter()
    fun getRockMusic(lifecycleCoroutineScope: LifecycleCoroutineScope)
    fun checkNetworkConnection()
}