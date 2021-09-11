package com.example.movie.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.movie.R
import com.example.movie.databinding.FragmentDetailBinding
import com.example.movie.util.Constants
import com.example.movie.util.Constants.BASE_IMG
import com.example.movie.util.Constants.glide
import com.example.movie.util.Constants.toolbarFunction




class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val args by navArgs<DetailFragmentArgs>()
    private var _binding:FragmentDetailBinding?=null
    private val binding get()= _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    @SuppressLint("ResourceType", "SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding= FragmentDetailBinding.inflate(inflater,container,false)

        binding.imageView.glide(Constants.BASE_IMG + args.datas!!.data!!.backdrop_path,requireContext())
        binding.textImdbDetailFragment.text=resources.getString(R.string.imdb)+ args.datas!!.data!!.vote_average
        binding.textViewTextOverviewDetailFragment.text= args.datas!!.data!!.overview
        if(args.datas?.data?.titler2==null){
                if(args.datas?.language=="tr"){
                    binding.textViewDetailFragmentTitle.text="${resources.getString(R.string.title_text_detail_tr)}${args.datas!!.data!!.title}"
                    binding.textReleasedTitleDetailFragment.text="${resources.getString(R.string.released_time_text_detail_fragment_tr)}${args.datas!!.data!!.release_date}"
                    binding.textViewDetailFragmentTitle.text="${resources.getString(R.string.title_text_detail_tr)}${args.datas!!.data!!.title}"
                    binding.textViewDetailOverview.text=resources.getString(R.string.overview_tr)
                    binding.imageViewType.glide(BASE_IMG+args.datas!!.data?.poster_path.toString(),requireContext())
                }else{
                    binding.textViewDetailFragmentTitle.text="${resources.getString(R.string.title_text_detail)}${args.datas!!.data!!.title}"
                    binding.textReleasedTitleDetailFragment.text="${resources.getString(R.string.released_time_text_detail_fragment)}${args.datas!!.data!!.release_date}"
                    binding.textViewDetailFragmentTitle.text="${resources.getString(R.string.title_text_detail)}${args.datas!!.data!!.title}"
                    binding.imageViewType.glide(BASE_IMG+args.datas!!.data?.poster_path.toString(),requireContext())
                }
        }else if(args.datas?.data?.title==null){
            if(args.datas?.language=="tr"){
                binding.textViewDetailFragmentTitle.text="${resources.getString(R.string.original_name_tr)}${args.datas!!.data!!.titler2}"
                binding.textReleasedTitleDetailFragment.text="${resources.getString(R.string.first_release_tr)}${args.datas?.data?.date}"
                binding.imageViewType.glide(BASE_IMG+args.datas!!.data?.poster_path.toString(),requireContext())
                binding.textViewDetailOverview.text=resources.getString(R.string.overview_tr)
            }else{
                binding.textViewDetailFragmentTitle.text="${resources.getString(R.string.original_name)}${args.datas!!.data!!.titler2}"
                binding.textReleasedTitleDetailFragment.text="${resources.getString(R.string.first_release)}${args.datas?.data?.date}"
                binding.imageViewType.glide(BASE_IMG+args.datas!!.data?.poster_path.toString(),requireContext())
            }
        }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val activity=(requireActivity() as AppCompatActivity)
        toolbarFunction(activity,binding.toolbarDetailFragment,this)
        binding.toolbarDetailFragment.title="Detaylar"
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onDestroyView() {
        _binding=null
        super.onDestroyView()
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_detail_fragment,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return false
    }
}