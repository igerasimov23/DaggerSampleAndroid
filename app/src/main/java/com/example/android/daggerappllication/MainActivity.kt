package com.example.android.daggerappllication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.daggerappllication.api.ApiInterface
import com.example.android.daggerappllication.api.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class MainActivity : AppCompatActivity(){
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<*>

//    @Inject
    lateinit var viewModel: MainViewModel

    private val receivedObserver = Observer(::onGroceryListResultReceived)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        manager = LinearLayoutManager(this)

        // TODO not update on device rotation
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        getAllData()
    }

    private fun getAllData(){
        viewModel.fetchData()
        viewModel.getLoadingStateLiveData.observe(this, receivedObserver)
    }

    private fun onGroceryListResultReceived(results: List<Article>) {
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply{
            adapter = MyAdapter(results)
            layoutManager = manager
        }
    }
}