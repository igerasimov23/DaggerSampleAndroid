package com.example.android.daggerappllication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.daggerappllication.api.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity(){
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<*>
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = LinearLayoutManager(this)
        getAllData()
    }

    private fun getAllData(){

        val service = ApiInterface.topStoriesApi
        GlobalScope.launch(Dispatchers.Main) {
            Log.i("start", "******: ")
            val postRequest = service.getAllData()
            try {
                val response = postRequest
                Log.i("TAG", "getAllData: $response")
                val results = response.body()?.articles

                recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply{
                    myAdapter = MyAdapter(results)
                    layoutManager = manager
                    adapter = myAdapter
                }

            } catch (e: Exception) {
                Toast.makeText(baseContext, "something went wrong.", Toast.LENGTH_LONG).show()
            }
        }



//        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply{
//            myAdapter = MyAdapter(listOfArticles)
//            layoutManager = manager
//            adapter = myAdapter
//        }

//        val apiInterface = ApiInterface.create().getAllData()
//       apiInterface.enqueue(object: Callback<List<com.example.android.daggerappllication.api.Article>>{
//            override fun onResponse(
//                call: Call<List<com.example.android.daggerappllication.api.Article>>,
//                response: Response<List<com.example.android.daggerappllication.api.Article>>
//            ) {
//                Log.d("TAG", "onResponse: ${response.body()}")
//                if(response.isSuccessful){
//                    recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply{
//                        myAdapter = MyAdapter(response.body()!!)
//                        layoutManager = manager
//                        adapter = myAdapter
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<List<com.example.android.daggerappllication.api.Article>>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })
    }
}