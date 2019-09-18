package com.debo.newsapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.debo.newsapp.NewsApplication
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