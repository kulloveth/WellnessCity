package com.wellnesscity.health.ui.healthtips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asFlow
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.wellnesscity.health.R
import com.wellnesscity.health.data.model.HealthTipX
import com.wellnesscity.health.data.model.Resource
import com.wellnesscity.health.data.model.Status
import com.wellnesscity.health.databinding.FragmentHealthTipsBinding
import com.wellnesscity.health.ui.welcome.WelcomeFragmentDirections
import com.wellnesscity.health.util.getQueryTextChangeStateFlow
import com.wellnesscity.health.util.snackMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

/**
*Created by Loveth Nwokike on 25/10/2020
 */
@AndroidEntryPoint
class HealthTipsFragment : Fragment() {
    private var binding:FragmentHealthTipsBinding? = null
    private val viewmodel:HealthTipsViewModel by viewModels()
    private val adapter = HealthAdapter()
    private var searchList:Resource< List<HealthTipX>>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHealthTipsBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listLl?.toolBar?.title = "Health Tips"
        binding?.listLl?.toolBar?.setNavigationOnClickListener {
        requireView().findNavController().navigate(HealthTipsFragmentDirections.actionHealthTipsFragmentToWelcomeFragment())
        }
        binding?.listLl?.rv?.adapter = adapter
        showData()
    }
    override fun onResume() {
        super.onResume()
        binding?.listLl?.shimmerFl?.startShimmerAnimation()
    }

    override fun onPause() {
        binding?.listLl?.shimmerFl?.stopShimmerAnimation()
        super.onPause()
    }
    fun showData() {
        viewmodel.fetchHealthData().observe(requireActivity(), Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding?.listLl?.shimmerFl?.stopShimmerAnimation()
                    adapter.submitList(it.data)
                    Timber.d("${it.data}")
                        searchList = it

                    binding?.listLl?.shimmerFl?.visibility = View.GONE
                    binding?.listLl?.rv?.visibility = View.VISIBLE
                }
                Status.ERROR ->{
                    it.message?.let {
                        requireView().snackMessage(it)
                    }
                } }
        })
    }

}