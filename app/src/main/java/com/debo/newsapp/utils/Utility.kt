package com.debo.newsapp.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.debo.newsapp.NewsApplication
import com.debo.newsapp.R
import com.google.android.material.snackbar.Snackbar
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}

fun isNetworkAvailable(): Boolean {
    val connectivityManager =
        NewsApplication.applicationContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting
}

fun convertDate(date: String): String {
    var df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
    var newDate: Date? = null
    try {
        newDate = df.parse(date)
        df = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return newDate?.let { df.format(newDate) } ?: date
}

fun View.isVisible(): Boolean = visibility == View.VISIBLE

fun View.isGone(): Boolean = visibility == View.GONE

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

fun loadImage(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .apply(
            RequestOptions()
                .diskCacheStrategy(
                    DiskCacheStrategy.ALL
                )
        ).into(view)
}

fun Activity.showOnUiThread(init: Activity.() -> Unit): Activity {
    if (!isFinishing || !isDestroyed) {
        runOnUiThread {
            init()
        }
    }
    return this
}

fun Activity.snackbarMessage(message: String) {
    try {
        showOnUiThread {
            val snackBar =
                Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
            val textView = snackBar.view.findViewById(R.id.snackbar_text) as TextView
            val robotoTypeface = ResourcesCompat.getFont(this, R.font.roboto_slab_regular)
            textView.typeface = robotoTypeface
            snackBar.show()
        }
    } catch (ex: Exception) {
    }
}

