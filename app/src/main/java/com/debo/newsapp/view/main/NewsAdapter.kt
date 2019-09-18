package com.debo.newsapp.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.debo.newsapp.R
import com.debo.newsapp.utils.convertDate
import com.debo.newsapp.view.detail.DetailActivity
import com.debo.newsapp.view.model.ProjectView

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var newsList: MutableList<ProjectView> = mutableListOf()

    fun addNews(news: MutableList<ProjectView>) {
        if (newsList.isNotEmpty()) {
            newsList.clear()
        }
        this.newsList = news
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_rv_headlines, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (newsList.size > 0) {
            newsList.size
        } else {
            0
        }
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    class NewsViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private var imvNews = itemView.findViewById<ImageView>(R.id.imvNews)
        private var txvNewsSource = itemView.findViewById<TextView>(R.id.txvNewsSource)
        private var txvDate = itemView.findViewById<TextView>(R.id.txvDate)
        private var txvTitle = itemView.findViewById<TextView>(R.id.txvTitle)
        private var parent = itemView

        fun bind(view: ProjectView) {
            txvNewsSource.text = view.source
            txvDate.text = convertDate(view.publishedAt)
            txvTitle.text = view.title

            Glide.with(imvNews.context)
                .load(view.urlToImage)
                .apply(
                    RequestOptions()
                        .diskCacheStrategy(
                            DiskCacheStrategy.ALL
                        )
                ).into(imvNews)

            itemView.setOnClickListener {
                parent.context.startActivity(DetailActivity.newIntent(parent.context, view.title))
            }
        }
    }
}