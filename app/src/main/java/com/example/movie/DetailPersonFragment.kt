package com.example.movie

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.adapter.TrendAdapter
import com.example.movie.databinding.FragmentDetailPersonBinding
import com.example.movie.model.TrendDetail
import com.example.movie.model.TrendModel
import com.example.movie.util.Constants.BASE_IMG
import com.example.movie.util.Constants.glide
import com.example.movie.util.Constants.toolbarFunction
import com.example.movie.view.DetailFragmentArgs
import com.example.movie.viewmodel.trendViewModel
import kotlinx.android.parcel.RawValue


class DetailPersonFragment : Fragment(R.layout.fragment_detail_person) {
    private var _binding:FragmentDetailPersonBinding?=null
    private val binding get() = _binding!!
    private val args by navArgs<DetailPersonFragmentArgs>()
    lateinit var viewModel:trendViewModel
    lateinit var adapter: TrendAdapter
    val dizi=ArrayList<TrendModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding= FragmentDetailPersonBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = (requireActivity() as AppCompatActivity)
        toolbarFunction(activity,binding.toolbarDetailPerson,this)
        binding.toolbarDetailPerson.title="Aktörün Detayları"
        val const=args.parcelClass!!.personality
        val link= const?.profile_path
        val lang= args.parcelClass!!.language
        if(args.parcelClass?.personality?.profile_path==null){
           binding.imageViewForPerson.setImageResource(resources.getIdentifier("cemal","drawable", requireContext().packageName))
        }else{
            binding.imageViewForPerson.glide(BASE_IMG+link,requireContext())
        }

        if(args.parcelClass?.language=="eng"){
            binding.textViewForPerson.text=resources.getString(R.string.oyuncunun_ad_eng)+const?.name
            binding.textViewYerAldigiDizi.text=resources.getString(R.string.yer_aldigi_dizi_ve_filmler_eng)+" "+const?.known_for?.size?.toString()
        }else{
            binding.textViewForPerson.text=resources.getString(R.string.oyuncunun_ad)+const?.name
            binding.textViewYerAldigiDizi.text=resources.getString(R.string.yer_ald_dizi_ve_filmler)+" "+const?.known_for?.size?.toString()
        }


        viewModel=ViewModelProvider(this).get(trendViewModel::class.java)
        adapter=TrendAdapter(requireContext(),2,lang)
        binding.recyclerView2.layoutManager=GridLayoutManager(requireContext(),2)
       val models= args.parcelClass!!.personality?.known_for
        val sending_class=TrendModel(1, models as @RawValue ArrayList<TrendDetail>?,1.0,1.0)
        dizi.add(sending_class)
        adapter.refreshData(dizi)
        binding.recyclerView2.adapter=adapter
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu,menu)
    }

}