package com.wellnesscity.health.ui.diet

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.wellnesscity.health.R
import com.wellnesscity.health.data.model.Status
import com.wellnesscity.health.databinding.FragmentDietBinding
import com.wellnesscity.health.util.snackMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by Loveth Nwokike on 28/10/2020
 * */
@AndroidEntryPoint
class DietFragment : Fragment() {
    private val viewModel: DietViewModel by viewModels()
    private var binding: FragmentDietBinding? = null
    private val adapter = DietAdapter()
    private var diet = ""

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
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding?.toolBar)
        binding?.toolBar?.setNavigationOnClickListener {
            requireView().findNavController()
                .navigate(DietFragmentDirections.actionDietFragmentToWelcomeFragment())
        }
        showData()
    }

    fun showData() {
        viewModel.fetchRecipeData(diet).observe(requireActivity(), Observer {
            viewModel.getDiet().observe(requireActivity(), Observer {
                diet = it
            })
            binding?.toolBar?.title = "Diets For $diet"
            when (it.status) {
                Status.SUCCESS -> {
                    binding?.shimmerFl?.stopShimmerAnimation()
                    binding?.rv?.layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    binding?.rv?.adapter = adapter
                    adapter.submitList(it.data?.results)
                    Timber.d("${it.data}")
                    binding?.shimmerFl?.visibility = View.GONE
                    binding?.rv?.visibility = View.VISIBLE
                    Timber.d("${it.data?.results?.size}")
                }
                Status.ERROR -> {
                    it.message?.let {
                        requireView().snackMessage(it)
                    }
                }
                Status.LOADING -> {
                    binding?.shimmerFl?.visibility = View.VISIBLE
                    binding?.shimmerFl?.startShimmerAnimation()
                    binding?.rv?.visibility = View.GONE
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            setupDiet(item.itemId)
            showData()
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        binding?.shimmerFl?.startShimmerAnimation()
    }

    override fun onPause() {
        binding?.shimmerFl?.stopShimmerAnimation()
        super.onPause()
    }

     fun setupDiet(id: Int) {
        when (id) {
            R.id.vegan -> {
                viewModel.saveDiet("vegan")
            }
            R.id.whole -> {
                viewModel.saveDiet("Whole30")
            }
            R.id.gf -> {
                viewModel.saveDiet("Gluton Free")
            }
            R.id.vegy -> {
                viewModel.saveDiet("Vegetarian")
            }
            R.id.ketogen -> {
                viewModel.saveDiet("Ketogenic")
            }
            R.id.lactovegy -> {
                viewModel.saveDiet("Lacto-vegetarian")
            }
            R.id.ovovegy -> {
                viewModel.saveDiet("Ovo-vegetarian")
            }
            R.id.pesce -> {
                viewModel.saveDiet("Pescetarian")
            }
            R.id.palio -> {
                viewModel.saveDiet("Paleo")
            }
            R.id.primal -> {
                viewModel.saveDiet("Primal")
            }
        }
    }

}