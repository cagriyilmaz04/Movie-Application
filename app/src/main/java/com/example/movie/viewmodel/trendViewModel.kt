package com.example.movie.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.model.TrendModel
import com.example.movie.util.Constants.retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class trendViewModel  : ViewModel() {
    val datas= MutableLiveData<TrendModel>()
    val loading= MutableLiveData<Boolean>()
    val tvDatas=MutableLiveData<TrendModel>()
    fun getData( a:String, b:String,c:String){
        viewModelScope.launch(Dispatchers.Main){
            loading.value=true
            val respond=retrofit.getTrends(a,b,c)
            if(respond.isSuccessful){
                respond.body()?.let {
                    datas.value=it
                    loading.value=false
                }
            }
        }
    }


}