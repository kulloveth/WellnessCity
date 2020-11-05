package com.wellnesscity.health.ui

import android.app.Dialog
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.widget.ViewPager2
import com.wellnesscity.health.R
import com.wellnesscity.health.databinding.FragmentCovidBinding
import com.wellnesscity.health.ui.intro.IntroSlide
import com.wellnesscity.health.ui.intro.IntroSliderAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.*

/**
 * Created by Loveth Nwokike on 25/10/2020
 */
@AndroidEntryPoint
class CovidFragment : DialogFragment() {
private var binding:FragmentCovidBinding? = null
    private var tts: TextToSpeech? = null
    private var textView: TextView? = null

    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "Wash your hands",
                "Always wash your hands with soap and water",
                "wash_hands.json"
            ),
            IntroSlide(
                "Social distance",
                "Keep a safe distance when in a crowded area",
                "distance.json"
            ),
            IntroSlide(
                "Wear your Face masks",
                "Put on your face mask when you are in the public",
                "covid19.json"
            )
        )
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCovidBinding.inflate(layoutInflater)
        binding?.toolBar?.setNavigationOnClickListener {
            NavHostFragment.findNavController(this).navigate(CovidFragmentDirections.actionCovidFragmentToWelcomeFragment())
        }
        introSliderAdapter.onTextPassed = {
            textView = it
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewPager?.adapter = introSliderAdapter
        binding?.indicator?.setViewPager(binding?.viewPager)
        binding?.viewPager?.registerOnPageChangeCallback(
            object :ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when(position){
                        0 -> speak()
                        1 -> speak()
                        2 -> speak()
                    }
                }
            }
        )

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        this.isCancelable = false
        return super.onCreateDialog(savedInstanceState)
    }

    fun speak(){
        tts = TextToSpeech(requireContext()) { status ->
            if (status == TextToSpeech.SUCCESS) {
                // set US English as language for tts
                val result = tts?.setLanguage(Locale.US)

                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Timber.e("The Language specified is not supported!")
                } else {
                    val text = textView!!.text.toString()
                    tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
                }

            } else {
                Timber.e("Initilization Failed!")
            }
        }
    }

    override fun onDestroy() {
        // Shutdown TTS
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()

    }
}