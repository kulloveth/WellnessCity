package com.wellnesscity.health.ui.healthtips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.wellnesscity.health.data.model.Status
import com.wellnesscity.health.databinding.FragmentHealthTipsBinding
import com.wellnesscity.health.ui.welcome.WelcomeFragmentDirections
import com.wellnesscity.health.util.snackMessage
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HealthTipsFragment : Fragment() {
    private var binding:FragmentHealthTipsBinding? = null
    private val viewmodel:HealthTipsViewModel by viewModels()
    private val adapter = HealthAdapter()


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
                    binding?.listLl?.rv?.adapter = adapter
                    adapter.submitList(it.data)
                    Timber.d("${it.data}")
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