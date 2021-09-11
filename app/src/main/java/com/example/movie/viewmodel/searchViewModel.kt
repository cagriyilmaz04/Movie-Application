package com.example.movie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.model.PersonModel
import com.example.movie.model.TrendModel
import com.example.movie.util.Constants.retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class searchViewModel:ViewModel() {
    val data=MutableLiveData<TrendModel>()
    val check=MutableLiveData<Boolean>()
    val checkPerson=MutableLiveData<PersonModel>()
    fun searchData(type:String,query:String){
        viewModelScope.launch(Dispatchers.Main) {
            check.value=true
            val respond=retrofit.search(type,query)
            if(respond.isSuccessful){
                respond.body()?.let {
                    data.value=it
                    check.value=false
                }
            }
        }
    }
    fun searchPersonData(query: String,lang:String){
        viewModelScope.launch(Dispatchers.Main) {
            check.value=true
            val respond= retrofit.searchPerson(query,lang)
            if(respond.isSuccessful){
                respond.body().let {
                    checkPerson.value=it!!
                    check.value=false
                }
            }
        }
    }
}