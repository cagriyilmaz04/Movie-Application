package com.example.movie.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.DetailPersonFragmentDirections

import com.example.movie.databinding.RecyclerRowBinding
import com.example.movie.model.DetailFragmentModel
import com.example.movie.model.TrendModel
import com.example.movie.util.Constants.BASE_IMG

import com.example.movie.util.Constants.glide
import com.example.movie.view.SearchFragmentDirections
import com.example.movie.view.TrendFragmentDirections

class TrendAdapter(val context: Context, val flag:Int, var preferedLanguage:String): RecyclerView.Adapter<TrendAdapter.TrendDetailAdapter>() {
    var emptlyList=ArrayList<TrendModel>()
    class TrendDetailAdapter(val binding: RecyclerRowBinding): RecyclerView.ViewHolder(binding.root) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendDetailAdapter {
        return TrendDetailAdapter(RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TrendDetailAdapter, position: Int) {
        holder.binding.imageViewTrend.glide(BASE_IMG+ emptlyList.get(0).result!!.get(position).poster_path,context)
        if(emptlyList.get(0).result!!.get(position).titler2==null){
            holder.binding.textViewTrendFilmAdi.text= emptlyList.get(0).result?.get(position)!!.title
        }else if(emptlyList.get(0).result!!.get(position).title==null){
            holder.binding.textViewTrendFilmAdi.text= emptlyList.get(0).result?.get(position)!!.titler2
        }
        holder.binding.textViewTrendImdb.text="IMDB: "+ emptlyList.get(0).result!!.get(position).vote_average.toString()
        holder.itemView.setOnClickListener {
            val send_datas=DetailFragmentModel(emptlyList.get(0).result!!.get(position),preferedLanguage)
            if(flag==0){
                val action=TrendFragmentDirections.actionTrendFragmentToDetailFragment(send_datas)
                Navigation.findNavController(it).navigate(action)
            }else if(flag==1){
               val action= SearchFragmentDirections.actionSearchFragmentToDetailFragment(send_datas)
                Navigation.findNavController(it).navigate(action)
            }
            else if(flag==2){
                val action=DetailPersonFragmentDirections.actionDetailPersonFragmentToDetailFragment(send_datas)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
    override fun getItemCount(): Int {
        return emptlyList.get(0).result!!.size

    }
    fun refreshData(list:List<TrendModel>){
        this.emptlyList= list as ArrayList<TrendModel>
        notifyDataSetChanged()
    }
    

}