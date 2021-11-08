package com.amir.rickandmorty.ui.fragments.episode

import androidx.lifecycle.ViewModelProvider
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
import com.amir.rickandmorty.databinding.EpisodeFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodeFragment : Fragment(), EpisodeAdapter.OnItemClickListener {

    private val episodeAdapter = EpisodeAdapter()
    private val viewModel: EpisodeViewModel by viewModel()
    private var _binding: EpisodeFragmentBinding? = null
    private val binding get() = _binding!!
    private var nextPageToken: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = EpisodeFragmentBinding.inflate(inflater,container,false)
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
        binding.episodesRv.adapter = episodeAdapter
        episodeAdapter.addListener(this)
    }

    private fun initialize(){
        viewModel.fetchEpisodes(nextPageToken).observe(viewLifecycleOwner, Observer { resources ->
            when(resources.status){
                Status.SUCCESS -> {
                    resources.data?.results?.let { episodeAdapter.addItems(it) }
                    binding.episodeProgressBar.visibility = View.GONE
                    val string = resources.data?.info?.next
                    val result = string?.replace(
                        "https://rickandmortyapi.com/api/episode?page=","",true
                    )
                    if (result !=null){
                        nextPageToken = result?.toInt()
                    }else{
                        nextPageToken = 0
                    }
                }
                Status.LOADING -> {
                    binding.episodeProgressBar.visibility = View.VISIBLE
                }
                Status.ERROR ->{
                    Toast.makeText(requireContext(),"Error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setRecyclerViewScrollListener(){
        binding.episodesRv.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager = binding.episodesRv.getLayoutManager() as LinearLayoutManager
                val pos = layoutManager.findLastCompletelyVisibleItemPosition()
                val numItems: Int = binding.episodesRv.getAdapter()!!.getItemCount()
                if (pos+1 == numItems){
                    initialize()
                }
            }
        })
    }

    override fun onClick(id: Int, type: Int) {
        val bundle = Bundle()
        bundle.putInt("idKey", id)
        bundle.putInt("typeKey", type)
        findNavController().navigate(R.id.detailFragment,bundle)

    }

}