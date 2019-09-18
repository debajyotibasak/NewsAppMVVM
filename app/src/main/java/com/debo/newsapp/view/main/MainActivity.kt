package com.debo.newsapp.view.main

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.debo.newsapp.R
import com.debo.newsapp.utils.*
import com.debo.newsapp.view.base.BaseActivity
import com.debo.newsapp.view.model.ProjectView
import com.debo.newsapp.view.state.ResourceState
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {

    override fun provideLayout(): Int = R.layout.activity_main

    override fun provideViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupTitleLetterSpacing()
        setupRecyclerView()
    }

    private fun setupTitleLetterSpacing() {
        txvHeadline.letterSpacing = 0.3f
    }

    private fun setupRecyclerView() {
        adapter = NewsAdapter()
        rvNews.layoutManager = LinearLayoutManager(this)
        rvNews.adapter = adapter
    }

    private fun observeData() {
        with(getViewModel()) {
            if (isNetworkAvailable()) {
                fetchNews()
            } else {
                fetchNewsFromDb()
            }

            observe(newsLiveData) {
                it?.let {
                    when (it.status) {
                        ResourceState.LOADING -> {
                            if (!rvNews.isVisible()) {
                                progress.makeVisible()
                            }
                        }
                        ResourceState.SUCCESS -> {
                            progress.makeGone()
                            rvNews.makeVisible()
                            populateRecyclerView(it.data?.toMutableList() ?: mutableListOf())
                        }
                        ResourceState.ERROR -> {

                        }
                    }
                }
            }
        }

    }

    private fun populateRecyclerView(list: MutableList<ProjectView>) {
        adapter.addNews(list)
    }

    override fun onStart() {
        super.onStart()

        observeData()
    }
}
