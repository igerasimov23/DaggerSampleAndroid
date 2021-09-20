package com.example.android.daggerappllication

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.android.daggerappllication.api.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel: ViewModel() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<*>

    init {

        val service = ApiInterface.topStoriesApi

        viewModelScope.launch {
            GlobalScope.launch(Dispatchers.Main) {
                Log.i("start", "******: ")
                val postRequest = service.getAllData()
                try {
                    val response = postRequest
                    Log.i("TAG", "getAllData: $response")
                    val results = response.body()?.articles

//                    recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply{
//                        myAdapter = MyAdapter(results)
//                        layoutManager = manager
//                        adapter = myAdapter
//                    }

                } catch (e: Exception) {
//                    Toast.makeText(coroutineContext, "something went wrong.", Toast.LENGTH_LONG).show()
                }
            }

        }
    }
}