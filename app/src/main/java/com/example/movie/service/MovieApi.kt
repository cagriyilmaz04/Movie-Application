package com.example.movie.service

import com.example.movie.model.PersonModel
import com.example.movie.model.TrendModel

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApi {


    @GET("trending/{media_type}/{time_window}?api_key=015f94962786433385679f67bfafd252")
    suspend fun getTrends(@Path("media_type") type:String, @Path("time_window") day_or_week:String, @Query("language") indeks:String): Response<TrendModel>


    @GET("search/{media_type}?api_key=015f94962786433385679f67bfafd252&page=1")
    suspend fun search(@Path("media_type") type:String,@Query("query") searchedThing:String):Response<TrendModel>

    @GET("search/person?api_key=015f94962786433385679f67bfafd252")
    suspend fun searchPerson(@Query("query") searchedThing:String,@Query("language") language:String):Response<PersonModel>
}