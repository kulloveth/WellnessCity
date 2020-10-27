package com.wellnesscity.health.ui.diet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.wellnesscity.health.R
import com.wellnesscity.health.data.model.Status
import com.wellnesscity.health.databinding.FragmentDietBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DietFragment : Fragment() {
    private val viewModel:DietViewModel by viewModels()
private var binding:FragmentDietBinding? = null
    private val adapter = DietAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentDietBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showData()
    }
    fun showData(){
        viewModel.fetchRecipeData("Ketogenic").observe(requireActivity(), Observer {
            when(it.status) {
                Status.SUCCESS-> {
                    binding?.shimmerFl?.stopShimmerAnimation()
                     binding?.rv?.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                    binding?.rv?.adapter = adapter
                    adapter.submitList(it.data?.results)
                    Timber.d("${it.data}")
                    binding?.shimmerFl?.visibility = View.GONE
                    binding?.rv?.visibility = View.VISIBLE
                    Timber.d("${it.data?.results?.size}")
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        binding?.shimmerFl?.startShimmerAnimation()
    }

    override fun onPause() {
        binding?.shimmerFl?.stopShimmerAnimation()
        super.onPause()
    }

}