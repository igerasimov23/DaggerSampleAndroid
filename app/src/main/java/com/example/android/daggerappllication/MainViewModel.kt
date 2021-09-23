package com.example.android.daggerappllication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.daggerappllication.api.Article
import com.example.android.daggerappllication.api.Retrofit
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val loadingStateLiveData: MutableLiveData<List<Article>> = MutableLiveData()
    val getLoadingStateLiveData: LiveData<List<Article>>
        get() = loadingStateLiveData


    init {
        fetchData()
    }
    fun fetchData() {
        val service = Retrofit.topStoriesApi

        viewModelScope.launch {
                try {
                    val postRequest = service.getAllData()

                    Log.i("TAG", "getAllData: $postRequest")
                    val results = postRequest.body()?.articles
                    loadingStateLiveData.postValue(results)


                } catch (e: Exception) {
                    Log.i("TAG", "fetchData: $e")
                }
        }
    }

}