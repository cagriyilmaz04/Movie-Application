package com.example.movie.util

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.movie.service.MovieApi
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Constants {
    const val BASE_URL="https://api.themoviedb.org/3/"
    const val BASE_IMG="https://image.tmdb.org/t/p/w500"
    fun ImageView.glide(url:String, context: Context){
        Glide.with(context).load(url).dontAnimate().into(this)

    }
     val retrofit=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieApi::class.java)


    fun toolbarFunction(activity:AppCompatActivity,toolbar: Toolbar,fragment:Fragment){
        fragment.setHasOptionsMenu(true)
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
       activity.supportActionBar!!.setDisplayShowHomeEnabled(true)

    }

}