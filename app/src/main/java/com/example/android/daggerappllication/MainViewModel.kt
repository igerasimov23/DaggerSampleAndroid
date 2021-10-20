package com.example.android.daggerappllication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.daggerappllication.api.Article
import com.example.android.daggerappllication.api.RetrofitModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
@Module
@InstallIn(SingletonComponent::class)
class MainViewModel @Inject constructor(

    private val service: RetrofitModule
) : ViewModel() {
    private val loadingStateLiveData: MutableLiveData<List<Article>> = MutableLiveData()
    val getLoadingStateLiveData: LiveData<List<Article>>
        get() = loadingStateLiveData


    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            try {
                val postRequest = service.topStoriesApi.getAllData()

                Log.i("TAG", "getAllData: $postRequest")
                val results = postRequest.body()?.articles
                loadingStateLiveData.postValue(results)
            } catch (e: Exception) {
                Log.i("TAG", "fetchData: $e")
            }
        }
    }

}