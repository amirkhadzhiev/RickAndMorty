package com.amir.rickandmorty.ui.fragments.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amir.rickandmorty.constants.const
import com.amir.rickandmorty.databinding.LocationItemBinding
import com.amir.rickandmorty.models.Location
import com.amir.rickandmorty.models.LocationModel
import java.util.*

class LocationAdapter: RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    private var list: List<LocationModel> = LinkedList()
    private lateinit var listener: LocationAdapter.OnItemClickListener

    fun addListener(listener: LocationAdapter.OnItemClickListener){
        this.listener = listener
    }

    open fun addItems(list: List<LocationModel>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationAdapter.ViewHolder {
        val binding = LocationItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationAdapter.ViewHolder, position: Int) {
       holder.onBind(list[position], listener)
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(private val binding: LocationItemBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(locationModel: LocationModel,listener: OnItemClickListener){
            binding.locationNameTv.setText(locationModel.name)
            binding.locationCreatedTv.setText(locationModel.created)
            binding.locationTypeTv.setText(locationModel.type)
            binding.root.setOnClickListener {
                listener.onClick(locationModel.id,const.LOCATION)
            }
        }
    }

    interface OnItemClickListener {
        fun onClick(id: Int, type: Int)
    }
}