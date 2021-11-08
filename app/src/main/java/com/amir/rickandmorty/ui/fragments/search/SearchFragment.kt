package com.amir.rickandmorty.ui.fragments.search


import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.amir.rickandmorty.R
import com.amir.rickandmorty.data.network.Status
import com.amir.rickandmorty.databinding.SearchFragmentBinding
import com.amir.rickandmorty.models.Character
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.ArrayList

class SearchFragment : Fragment(), SearchAdapter.OnItemClickListener {

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!
    private val searchAdapter = SearchAdapter()
    private val viewModel : SearchViewModel by viewModel()
    private lateinit var name: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchFragmentBinding.inflate(inflater, container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupViews()
    }

    private fun initialize(){
        binding.searchRv.adapter = searchAdapter
        searchAdapter.addListener(this)
    }

    private fun setupViews(){
        binding.searchBtn.setOnClickListener {
            searchAdapter.clearAdapter()
            dismissKeyboard(requireActivity())
            name = binding.searchEt.text.toString()
            fetchData(name)
        }
    }

    private fun fetchData(name: String){
        viewModel.fetchFilteredData(name).observe(viewLifecycleOwner, Observer { resources ->
            when (resources.status){
                Status.SUCCESS -> {
                    resources.data?.results?.let { searchAdapter.addItems(it as ArrayList<Character>) }
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(),"Error", Toast.LENGTH_SHORT).show()
                }
            }
        })
        viewModel.fetchFilteredDataLoc(name).observe(viewLifecycleOwner, Observer { resources ->
            when(resources.status){
                Status.SUCCESS -> {
                    resources.data?.results?.let { searchAdapter.addItems(it as ArrayList<Character>) }
                }
            }
        })
        viewModel.fetchFilteredDataEpisodes(name).observe(viewLifecycleOwner, Observer { resources ->
            when(resources.status){
                Status.SUCCESS -> {
                    resources.data?.results?.let { searchAdapter.addItems(it as ArrayList<Character>) }
                }
                Status.ERROR ->{
                    Toast.makeText(requireContext(), "Episode Error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


    fun dismissKeyboard(activity: Activity){
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (null != activity.currentFocus) imm.hideSoftInputFromWindow(
            activity.currentFocus!!.applicationWindowToken, 0
        )
    }

    override fun onClick(id: Int, type: Int) {
        val bundle = Bundle()
        bundle.getInt("idKey", id)
        bundle.getInt("typeKey", type)
        findNavController().navigate(R.id.detailFragment,bundle)
    }

}