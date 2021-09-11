package com.example.movie.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.R
import com.example.movie.adapter.PersonAdapter
import com.example.movie.adapter.TrendAdapter
import com.example.movie.databinding.FragmentSearchBinding
import com.example.movie.model.PersonModel
import com.example.movie.model.Personality
import com.example.movie.model.TrendDetail
import com.example.movie.model.TrendModel
import com.example.movie.util.Constants.toolbarFunction
import com.example.movie.viewmodel.searchViewModel

class SearchFragment : Fragment(R.layout.fragment_search) {
    private var _binding: FragmentSearchBinding?=null
    private val binding get()=_binding!!
    lateinit  var dizi:Array<String>
    var indeks=-1
    lateinit var dil_dizi:Array<String>
    lateinit var Adapter:TrendAdapter
    lateinit var viewModel:searchViewModel
    lateinit var AdapterPerson:PersonAdapter
    var string_language="eng"
    val result=ArrayList<TrendDetail>()
    var diziler= TrendModel(result = result)
    var resultPerson=ArrayList<Personality>()
    var diziPerson=PersonModel(results = resultPerson)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding= FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity=(requireActivity() as AppCompatActivity)
        binding.textViewResult.visibility=View.INVISIBLE
        toolbarFunction(activity,binding.toolbarSearchFragment,this)
        binding.toolbarSearchFragment.title="Arama"
        dizi=resources.getStringArray(R.array.filter1)
        var str="eng"
        Adapter= TrendAdapter(requireContext(),1,str)
        binding.recyclerViewSearch.layoutManager=GridLayoutManager(requireContext(),2)
        viewModel=ViewModelProvider(this).get(searchViewModel::class.java)
        val adapter=ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,dizi)
        binding.spinnerSearchFragment.adapter=adapter
        AdapterPerson= PersonAdapter(requireContext(),str)
        binding.spinnerSearchFragment.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
               indeks=position
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        dil_dizi=resources.getStringArray(R.array.dili_sec)
        val adapterSpinnerLang=ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,dil_dizi)
        binding.spinnerLanguageSearch.adapter=adapterSpinnerLang
        binding.spinnerLanguageSearch.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position>0){
                    when(position){
                        1->{
                            string_language="tr"
                            Adapter.preferedLanguage=string_language
                        }
                        2->{
                            string_language="eng"
                            Adapter.preferedLanguage=string_language
                        }
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        binding.textViewError.visibility=View.INVISIBLE
        binding.buttonAraSearchFragment.setOnClickListener {
            val query=binding.textQuery.text.toString()
            if(query.length>=1 &&indeks!=0){

                observeData(query,indeks,string_language)

            }
        }
    }
    @SuppressLint("SetTextI18n")
    fun observeData(query:String, indeks:Int,language:String){
        if(indeks==1||indeks==3){
            val returned_string=findType(indeks)
            viewModel.searchData(returned_string,query)
            viewModel.data.observe(viewLifecycleOwner){

                diziler=it
                val temp=ArrayList<TrendModel>()
                temp.add(diziler)
                Adapter.refreshData(temp)
                binding.recyclerViewSearch.adapter?.notifyDataSetChanged()
                if(diziler.result?.size==0){
                    binding.textViewError.visibility=View.VISIBLE
                }else{
                    binding.textViewError.visibility=View.INVISIBLE
                    binding.recyclerViewSearch.visibility=View.VISIBLE
                    binding.recyclerViewSearch.adapter=Adapter
                    binding.textViewResult.visibility=View.VISIBLE
                    binding.textViewResult.text= temp.get(0).total_results!!.toInt().toString()+" Sonuç Bulundu"
                }
            }

        }else if(indeks==2){
                viewModel.searchPersonData(query,language)
            viewModel.checkPerson.observe(viewLifecycleOwner){
                    diziPerson=it
                    val temp=ArrayList<PersonModel>()
                    temp.add(diziPerson)
                    AdapterPerson.refreshDatas(temp)
                AdapterPerson.prefLanguage=language
                    binding.recyclerViewSearch.adapter=AdapterPerson
                    binding.recyclerViewSearch.adapter?.notifyDataSetChanged()
                binding.textViewResult.visibility=View.VISIBLE
                binding.textViewResult.text= temp.get(0).total_results!!.toInt().toString()+" Sonuç Bulundu"
            }
        }
    }
    private fun findType(indeks: Int): String {
        var temp=""
        when(indeks){
            1->{
                temp="tv"
            }
            2->{
               temp="person"
            }
            3->{
                temp="movie"
            }
        }
        return temp
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }


}