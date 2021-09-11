package com.example.movie.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

data class PersonModel(
        val page:Int?=null,
        val results: ArrayList<Personality> ,
        val total_pages:Int?=null,
        val total_results:Int?=null)
data class Personality(
        val adult:Boolean?=null,
        val gender:Int?=null,
        val id:Long?=null,
        @SerializedName("known_for")
        val known_for:List<TrendDetail>?=null,
        val known_for_department :String?=null,
        val name:String?=null,
        val popularity:Double?=null,
        val profile_path:String?=null)
data class Known(
        val backdrop_path:String?=null,
        val first_air_date:String?=null,
        val genre_ids:List<Int> ? =null,
        val id:Long ? =null,
        val media_type : String?=null,
        val name:String?=null,
        val release_date:String?=null,
        val origin_country :List<String> ? =null,
        val original_language:String?=null,
        val overview:String?=null,
        val poster_path:String?=null,
        val vote_average:Double?=null,
        val vote_count:Int?=null)
@Parcelize
data class parcelableClass(val personality:@RawValue Personality ?=null,val language:String):Parcelable