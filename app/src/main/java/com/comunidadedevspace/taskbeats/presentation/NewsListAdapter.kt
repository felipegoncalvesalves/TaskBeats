package com.comunidadedevspace.taskbeats.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.comunidadedevspace.taskbeats.R
import com.comunidadedevspace.taskbeats.data.local.News

class NewsListAdapter : ListAdapter<News, NewListViewHOlder> (NewsListAdapter){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewListViewHOlder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return NewListViewHOlder(view)    }

    override fun onBindViewHolder(holder: NewListViewHOlder, position: Int) {
        val news = getItem(position)
        holder.bimd(news)
    }

    companion object: DiffUtil.ItemCallback<News>(){
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.title == newItem.title &&
                    oldItem.imgUrl == newItem.imgUrl
        }
    }
}

class NewListViewHOlder(
    private val  view: View
) : RecyclerView.ViewHolder(view){
    private val tvTitle = view.findViewById<TextView>(R.id.tv_news_title)
    private val imgNews = view.findViewById<ImageView>(R.id.iv_news)

    fun bimd(
        news: News
    ){
        tvTitle.text = news.title
        imgNews.load(news.imgUrl){
            transformations(RoundedCornersTransformation(25f))
        }
    }
}