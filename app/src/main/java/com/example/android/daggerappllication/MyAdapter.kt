package com.example.android.daggerappllication

import com.example.android.daggerappllication.api.Article
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(private val data: List<Article>?) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(article: Article?) {
            val title = view.findViewById<TextView>(R.id.tvTitle)
            val imageView = view.findViewById<ImageView>(R.id.imageView)
            val description = view.findViewById<TextView>(R.id.tvDescription)

            title.text = article?.title
            description.text = article?.description


            Glide.with(view.context).load(article?.urlToImage).centerCrop().into(imageView)
        }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data?.get(position))
    }

    override fun getItemCount(): Int {
        return data!!.size
    }
}