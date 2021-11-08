package com.amir.rickandmorty.ui.fragments.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amir.rickandmorty.R
import com.amir.rickandmorty.constants.const
import com.amir.rickandmorty.databinding.CharacterItemBinding
import com.bumptech.glide.Glide
import com.amir.rickandmorty.models.Character
import java.util.*

class CharacterAdapter: RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    private var list: List<Character> = LinkedList()

    private lateinit var listener: CharacterAdapter.OnItemClickListener

    fun addListener(listener: CharacterAdapter.OnItemClickListener){
        this.listener = listener
    }

    open fun addItems(list: List<Character>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterAdapter.ViewHolder, position: Int) {
        holder.onBind(list[position],listener)
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(val binding: CharacterItemBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(character: Character, listener: OnItemClickListener){
            Glide.with(binding.chartersIv.context).load(character.image)
                .centerCrop().placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.chartersIv)
            binding.charterNameTv.setText(character.name)
            binding.charterStatusTv.setText(character.status)
            binding.charterCreatedTv.setText(character.created)
            binding.root.setOnClickListener {
                character.id?.let { it1 -> listener.onClick(it1, const.CHARACTER) }
            }
        }
    }

    interface OnItemClickListener {
        fun onClick(id: Int, type: Int)
    }
}
