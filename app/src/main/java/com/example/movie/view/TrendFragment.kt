package com.example.movie.view

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.R
import com.example.movie.adapter.TrendAdapter

import com.example.movie.databinding.FragmentTrendBinding
import com.example.movie.model.*
import com.example.movie.util.Constants.toolbarFunction
import com.example.movie.viewmodel.trendViewModel


class TrendFragment : Fragment(R.layout.fragment_trend) {
    private lateinit var binding : FragmentTrendBinding
    private lateinit var viewModel:trendViewModel
    lateinit var adapter:TrendAdapter
    val result=ArrayList<TrendDetail>()
    var dizi=TrendModel(result = result)
    var type_flag=0
    var type_language=0
    var type_time=0
    var str="eng"
    lateinit var type:Array<String>
    lateinit var language:Array<String>
    lateinit var time:Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_trend,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding= FragmentTrendBinding.bind(view)
        val activity= (requireActivity() as AppCompatActivity)
        toolbarFunction(activity,binding.toolbar,this)

        binding.toolbar.title="Trendler"

        viewModel= ViewModelProvider(requireActivity()).get(trendViewModel::class.java)
        binding.recyclerViewTrend.layoutManager= GridLayoutManager(requireContext(),2)
        binding.recyclerViewTrend.setHasFixedSize(true)
         type=resources.getStringArray(R.array.type)
        val adapter_type=ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,type)
        binding.spinnerType.adapter=adapter_type
        language=resources.getStringArray(R.array.dili_sec)
        val adapter_language=ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,language)
        binding.spinnerLanguage.adapter=adapter_language
        time=resources.getStringArray(R.array.time)
        val adapter_time=ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,time)
        binding.spinnerTime.adapter=adapter_time
        observeData(type_flag,type_language,type_time)

        binding.spinnerType.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position>0){
                    type_flag=position
                    observeData(type_flag,type_language,type_time)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        binding.spinnerLanguage.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position>0){
                    type_language=position
                    observeData(type_flag,type_language,type_time)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    binding.spinnerTime.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            if(position>0){
                type_time=position
                observeData(type_flag,type_language,type_time)
            }
        }
        override fun onNothingSelected(parent: AdapterView<*>?) {
        }
    }
    }
    fun observeData(flag_type:Int,flag_lang:Int,flag_time:Int){
        var time_str=""
        var lang_str=""
        var type_str=""
        if(flag_type==0||flag_type==1){
            type_str="movie"
            str
        }else if(flag_type==2){
            type_str="tv"
        }else if(flag_type==3){
            type_str="person"
        }
        if(flag_time==0||flag_time==1){
            time_str="day"
        }else if(flag_time==2){
            time_str="week"
        }
        if(flag_lang==0||flag_lang==2){
            lang_str="en"
        }else if(flag_lang==1){
            lang_str="tr"
        }
        adapter= TrendAdapter(requireContext(),0,lang_str)
        when(flag_type){
            in 0..2 ->{
                viewModel.getData(type_str,time_str,lang_str)
                viewModel.datas.observe(viewLifecycleOwner){
                    dizi=it
                    val temp=ArrayList<TrendModel>()
                    temp.add(dizi)
                    adapter.refreshData(temp)

                    binding.recyclerViewTrend.adapter=adapter
                    binding.recyclerViewTrend.adapter?.notifyDataSetChanged()
                }
                checkProgressBar()
            }
            3->{


            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_trend,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when(item.itemId){

            }
        return false
    }
    private fun checkProgressBar(){
        viewModel.loading.observe(viewLifecycleOwner){
            if (it==true){
                binding.progressBarTrendFragment.visibility=View.VISIBLE
            }
            if(it==false){
                binding.progressBarTrendFragment.visibility=View.INVISIBLE
            }
        }

    }


}