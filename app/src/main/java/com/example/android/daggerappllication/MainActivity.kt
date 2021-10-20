package com.example.android.daggerappllication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.daggerappllication.api.Article
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager

    @Inject
    lateinit var viewModel: MainViewModel

    private val receivedObserver = Observer(::onResultReceived)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        manager = LinearLayoutManager(this)

        getAllData()
    }

    private fun getAllData() {
        viewModel.getLoadingStateLiveData.observe(this, receivedObserver)
    }

    private fun onResultReceived(results: List<Article>) {
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            adapter = MyAdapter(results)
            layoutManager = manager
        }
    }
}