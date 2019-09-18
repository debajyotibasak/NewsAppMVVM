package com.debo.newsapp.view.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.debo.newsapp.R
import com.debo.newsapp.utils.convertDate
import com.debo.newsapp.utils.loadImage
import com.debo.newsapp.utils.observe
import com.debo.newsapp.utils.snackbarMessage
import com.debo.newsapp.view.base.BaseActivity
import com.debo.newsapp.view.model.ProjectView
import com.debo.newsapp.view.state.ResourceState
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity<DetailViewModel>() {

    companion object {
        const val STRING_EXTRA = "title"

        fun newIntent(context: Context, title: String): Intent {
            return Intent(context, DetailActivity::class.java)
                .putExtra(STRING_EXTRA, title)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }

    private lateinit var titleString: String

    override fun provideLayout(): Int = R.layout.activity_detail

    override fun provideViewModelClass(): Class<DetailViewModel> = DetailViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        titleString = if (intent.extras != null) {
            intent.getStringExtra(STRING_EXTRA) ?: ""
        } else {
            ""
        }

        setFullScreenActivity()
    }

    private fun setFullScreenActivity() {
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    override fun onStart() {
        super.onStart()

        observeData()
        setupBackButton()
    }

    private fun observeData() {
        with(getViewModel()) {
            if (titleString.isNotEmpty()) {
                fetchNewsFromDb(titleString)

                observe(newsLiveData) {
                    it?.let {
                        when (it.status) {
                            ResourceState.LOADING -> {
                            }
                            ResourceState.SUCCESS -> {
                                it.data?.let {
                                    populateUi(it)
                                }
                            }
                            ResourceState.ERROR -> {
                                snackbarMessage("Error Loading Data From Database")
                            }
                        }
                    }
                }
            }
        }
    }

    private fun populateUi(data: ProjectView) {
        txvContent.text = data.description
        txvDate.text = convertDate(data.publishedAt)
        loadImage(imvBackGround, data.urlToImage)
        txvTitle.text = data.title
        txvNewsSource.text = data.source
    }

    private fun setupBackButton() {
        imvBack.setOnClickListener {
            onBackPressed()
        }
    }
}