package com.example.navigation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_text.*

class TextFragment : Fragment(R.layout.fragment_text) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textView.setOnClickListener {
            val bundle = bundleOf("Name" to "MyArg")
            findNavController().navigate(R.id.open_button_action, bundle)
        }
    }
}