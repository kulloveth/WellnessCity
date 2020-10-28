package com.wellnesscity.health.ui.diet

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.wellnesscity.health.R
import com.wellnesscity.health.data.model.Status
import com.wellnesscity.health.databinding.FragmentDietBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class DietFragment : Fragment() {
    private val viewModel: DietViewModel by viewModels()
    private var binding: FragmentDietBinding? = null
    private val adapter = DietAdapter()
    private var diet = ""

    @Inject
    lateinit var prefs: DataStore<Preferences>
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
        showData()
    }

    fun showData() {
        viewModel.fetchRecipeData(diet).observe(requireActivity(), Observer {
            lifecycleScope.launch {
                prefs.data.collectLatest {
                    diet = it[preferencesKey<String>("diet")]?:"vegetarian"
                }
            }
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
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        lifecycleScope.launch(Dispatchers.Main) {
            setupDiet(item.itemId)
            showData()
        }

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

    suspend fun setupDiet(id: Int) {
        when (id) {
            R.id.vegan -> {
                prefs.edit {
                    it[preferencesKey<String>("diet")] = "vegan"
                }
            }
            R.id.whole -> {
                prefs.edit {
                    it[preferencesKey<String>("diet")] = "Whole30"
                }
            }
            R.id.gf -> {
                prefs.edit {
                    it[preferencesKey<String>("diet")] = "Gluton Free"
                }
            }
            R.id.vegy -> {
                prefs.edit {
                    it[preferencesKey<String>("diet")] = "Vegetarian"
                }
            }
            R.id.ketogen -> {
                prefs.edit {
                    it[preferencesKey<String>("diet")] = "Ketogenic"
                }
            }
            R.id.lactovegy -> {
                prefs.edit {
                    it[preferencesKey<String>("diet")] = "Lacto-vegetarian"
                }
            }
            R.id.ovovegy -> {
                prefs.edit {
                    it[preferencesKey<String>("diet")] = "Ovo-vegetarian"
                }
            }
            R.id.pesce -> {
                prefs.edit {
                    it[preferencesKey<String>("diet")] = "Pescetarian"
                }
            }
            R.id.palio -> {
                prefs.edit {
                    it[preferencesKey<String>("diet")] = "Paleo"
                }
            }
            R.id.primal -> {
                prefs.edit {
                    it[preferencesKey<String>("diet")] = "Primal"
                }
            }
        }
    }

}