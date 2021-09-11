package com.example.movie.adapter

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.databinding.PersonRescyclerRowBinding
import com.example.movie.databinding.RecyclerRowBinding
import com.example.movie.model.PersonModel
import com.example.movie.model.Personality
import com.example.movie.model.parcelableClass
import com.example.movie.util.Constants.BASE_IMG
import com.example.movie.util.Constants.glide
import com.example.movie.view.SearchFragmentDirections
import java.lang.Exception

class PersonAdapter(val context:Context, var prefLanguage:String): RecyclerView.Adapter<PersonAdapter.PersonVH>() {
    var empty= emptyList<PersonModel>()
    class PersonVH(val binding:PersonRescyclerRowBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonVH {
      return PersonVH(PersonRescyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }

    override fun onBindViewHolder(holder: PersonVH, position: Int) {
        try {
            if(empty.get(0).results.get(position).profile_path==null){

                holder.binding.imageViewKisiAdi.setImageResource(context.resources.getIdentifier("cemal","drawable",context.packageName))
            }else{

                holder.binding.imageViewKisiAdi.glide(BASE_IMG+empty.get(0).results.get(position).profile_path,context)
            }

            holder.binding.textViewKisiAdi.text= empty.get(0).results.get(position).name
            holder.itemView.setOnClickListener {
                val a=empty.get(0).results.get(position).known_for
                val person=Personality(empty.get(0).results.get(position).adult,empty.get(0).results.get(position).gender,empty.get(0).results.get(position).id,a,empty.get(0).results.get(position).known_for_department,empty.get(0).results.get(position).name,empty.get(0).results.get(position).popularity,empty.get(0).results.get(position).profile_path)
                val sending=parcelableClass(person,prefLanguage)
                val action=SearchFragmentDirections.actionSearchFragmentToDetailPersonFragment(sending)
                Navigation.findNavController(it).navigate(action)
            }
        }catch (e:Exception){
        }
    }
    override fun getItemCount(): Int {
        return empty.get(0).results.size
    }
    fun refreshDatas(list:List<PersonModel>){
        this.empty=list
        notifyDataSetChanged()

    }

}