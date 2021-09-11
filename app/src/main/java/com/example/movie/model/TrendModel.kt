package com.example.movie.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class TrendModel(
        val page:Int?=null,
        @SerializedName("results")
        val result: @RawValue ArrayList<TrendDetail> ? =null,
        val total_pages:Double?=null,
        val total_results:Double?=null):Parcelable