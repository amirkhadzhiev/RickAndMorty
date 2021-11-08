package com.amir.rickandmorty.ui.fragments.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amir.rickandmorty.R
import com.amir.rickandmorty.data.network.Status
import com.amir.rickandmorty.databinding.LocationFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class LocationFragment : Fragment(),LocationAdapter.OnItemClickListener {

    private var _binding: LocationFragmentBinding? = null
    private val binding get() = _binding!!
    private val locationAdapter = LocationAdapter()
    private val viewModel: LocationViewModel by viewModel()
    private var nextPageToken: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LocationFragmentBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setRecyclerViewScrollListener()
        setupViews()
    }
    private fun setupViews(){
        binding.locationRv.adapter = locationAdapter
        locationAdapter.addListener(this)
    }

    private fun setRecyclerViewScrollListener(){
        binding.locationRv.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager = binding.locationRv.getLayoutManager() as LinearLayoutManager
                val pos = layoutManager.findLastCompletelyVisibleItemPosition()
                val numItems : Int = binding.locationRv.getAdapter()!!.getItemCount()
                if (pos + 1 == numItems){
                    initialize()
                }
            }
        })
    }

    private fun initialize(){
        viewModel.fetchLocation(nextPageToken).observe(viewLifecycleOwner, Observer { resources ->
            when(resources.status){
                Status.SUCCESS -> {
                    resources.data?.results?.let { locationAdapter.addItems(it) }
                    binding.locationProgressBar.visibility = View.GONE
                    val string = resources.data?.info?.next
                    val result = string?.replace(
                        "https://rickandmortyapi.com/api/location?page=","",true
                    )
                    if (result != null){
                        nextPageToken = result?.toInt()
                    }else {
                        nextPageToken = 0
                    }
                }
                Status.LOADING -> {
                    binding.locationProgressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onClick(id: Int, type: Int) {
        val bundle = Bundle()
        bundle.getInt("idKey",id)
        bundle.getInt("typeKey",type)
        findNavController().navigate(R.id.detailFragment,bundle)
    }

}