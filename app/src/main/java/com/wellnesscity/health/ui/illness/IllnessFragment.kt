package com.wellnesscity.health.ui.illness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.wellnesscity.health.data.model.Status
import com.wellnesscity.health.databinding.FragmentIllnessBinding
import com.wellnesscity.health.ui.healthtips.HealthTipsFragmentDirections
import com.wellnesscity.health.util.snackMessage
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Created by Loveth Nwokike on 25/10/2020
 */
@AndroidEntryPoint
class IllnessFragment : Fragment() {
    private var binding: FragmentIllnessBinding? = null
    private val viewmodel: IllnessViewModel by viewModels()
    private val adapter = IllnessAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIllnessBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        binding?.listLl?.shimmerFl?.startShimmerAnimation()
    }

    override fun onPause() {
        binding?.listLl?.shimmerFl?.stopShimmerAnimation()
        super.onPause()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listLl?.toolBar?.setNavigationOnClickListener {
            requireView().findNavController().navigate(IllnessFragmentDirections.actionIllnessFragmentToWelcomeFragment())
        }
        showData()
    }
    fun showData() {
        viewmodel.fetchIllnessData().observe(requireActivity(), Observer {
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
                }

            }

        })
    }
}