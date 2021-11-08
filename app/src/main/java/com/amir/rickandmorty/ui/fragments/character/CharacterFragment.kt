package com.amir.rickandmorty.ui.fragments.character

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.amir.rickandmorty.R
import com.amir.rickandmorty.data.network.Status
import com.amir.rickandmorty.databinding.CharacterFragmentBinding


class CharacterFragment : Fragment(), CharacterAdapter.OnItemClickListener {

    private val characterAdapter =  CharacterAdapter()
    private var _binding: CharacterFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharacterViewModel by viewModel()
    private var nextPageToken: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CharacterFragmentBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterAdapter.addListener(this)
        initialize()
        setRecyclerViewScrollListener()
        setupViews()
    }

    fun initialize(){
        viewModel.fetchCharacters(nextPageToken).observe(viewLifecycleOwner, androidx.lifecycle.Observer { resources ->
            when(resources.status){
                Status.SUCCESS ->{
                    resources.data?.let { characterAdapter.addItems(it.results) }
                    binding.characterProgressBar.visibility = View.GONE
                    val string = resources.data?.info?.next
                    val results = string?.replace(
                        "https://rickandmortyapi.com/api/character?page=","", true)
                    if (results != null){
                        nextPageToken = results?.toInt()
                    }else {
                        nextPageToken = 0
                    }
                }
                Status.LOADING -> {
                    binding.characterProgressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(),"Error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setRecyclerViewScrollListener(){
        binding.characterRv.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager = binding.characterRv.getLayoutManager() as LinearLayoutManager
                val pos = layoutManager.findLastCompletelyVisibleItemPosition()
                val numItems: Int = binding.characterRv.getAdapter()!!.getItemCount()
                if (pos+1 == numItems){
                    initialize()
                }
            }
        })
    }

    fun setupViews() {
        binding.characterRv.adapter = characterAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(id: Int, type: Int) {
        val bundle = Bundle()
        bundle.putInt("idKey",id)
        bundle.putInt("typeKey", type)
        findNavController().navigate(R.id.detailFragment,bundle)
    }
}