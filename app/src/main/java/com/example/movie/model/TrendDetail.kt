package com.example.movie.model

import com.google.gson.annotations.SerializedName

data class TrendDetail(
        val vote_average:Double?=null,
        val title:String?=null,
        @SerializedName("original_name")
        val titler2:String?=null,
        val overview:String?=null,
        val release_date:String?=null,
        val adult:Boolean?=null,
        val backdrop_path:String?=null,
        @SerializedName("first_air_date")
        val date:String?=null,
        val genre_ids:List<Double>?=null,
        val vote_count:Double?=null,
        val original_language:String?=null,
        val original_title:String?=null,
        val poster_path:String?=null,
        val id:Double?=null,
        val video:Boolean?=null,
        val popularity:Double?=null,
        val media_type:String?=null

)