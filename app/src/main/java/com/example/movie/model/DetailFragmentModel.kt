package com.example.movie.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class DetailFragmentModel(var data: @RawValue TrendDetail?=null, val language:String):Parcelable