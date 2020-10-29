package com.wellnesscity.health.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.wellnesscity.health.R

class CovidFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_covid, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //this.isCancelable = false
        return super.onCreateDialog(savedInstanceState)
    }
}