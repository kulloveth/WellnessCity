package com.wellnesscity.health.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.wellnesscity.health.R
import com.wellnesscity.health.data.FirebaseServices
import com.wellnesscity.health.databinding.FragmentWelcomeBinding
import com.wellnesscity.health.util.setMenuBackgroundColor
import com.wellnesscity.health.util.tint
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class WelcomeFragment : Fragment() {
    private var binding: FragmentWelcomeBinding? = null
    @Inject  lateinit var db:FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWelcomeBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fs= FirebaseServices(db, requireActivity())
        changeDrawableCOlor()
        //binding?.topBarTv?.text?.toString()?.subSequence(14,41).toString()
        //binding?.barIv?.load("https://firebasestorage.googleapis.com/v0/b/wellnesscity-efcc6.appspot.com/o/illness%2Farthritis.jpg?alt=media&token=e5499ddf-fcda-44ad-9315-c383ee33e0f4")
    }

fun changeDrawableCOlor(){
    ResourcesCompat.getDrawable(resources,R.drawable.menu_bg,resources.newTheme())?.tint(requireContext(),R.color.menuTwo)
    binding?.dietTv?.setBackgroundDrawable(resources.getDrawable(R.drawable.menu_bg))
    ResourcesCompat.getDrawable(resources,R.drawable.menu_bg,resources.newTheme())?.tint(requireContext(),R.color.menuThree)
    binding?.illnessTv?.setBackgroundDrawable(resources.getDrawable(R.drawable.menu_bg))
    ResourcesCompat.getDrawable(resources,R.drawable.menu_bg,resources.newTheme())?.tint(requireContext(),R.color.menuFour)
    binding?.covidTv?.setBackgroundDrawable(resources.getDrawable(R.drawable.menu_bg))
}
}