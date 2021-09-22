package com.example.android.daggerappllication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.daggerappllication.api.Article

class MyAdapter(private val data: List<Article>?) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder constructor (itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: Article?) {
            val title = itemView.findViewById<TextView>(R.id.tvTitle)
            val imageView = itemView.findViewById<ImageView>(R.id.imageView)
            val description = itemView.findViewById<TextView>(R.id.tvDescription)

            title.text = article?.title
            description.text = article?.description

            Glide.with(itemView.context).load(article?.urlToImage).centerCrop().into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data?.get(position))
        val context = holder.itemView.context
        holder.itemView
        holder.itemView.setOnClickListener {
            ContextCompat.startActivity(context, NewsDetailActivity.newIntent(context, data?.get(position)?.url), null)
        }
    }

    override fun getItemCount(): Int {
        return data!!.size
    }
}