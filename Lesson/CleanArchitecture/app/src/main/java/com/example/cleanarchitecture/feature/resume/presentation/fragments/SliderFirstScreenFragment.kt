package com.example.cleanarchitecture.feature.resume.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.feature.resume.presentation.SlideState
import com.example.cleanarchitecture.feature.resume.presentation.SlideViewModel
import com.example.cleanarchitecture.feature.resume.presentation.SlideViewModelFactory
import kotlinx.android.synthetic.main.slider_first_fragment.view.*
import kotlinx.android.synthetic.main.slider_second_fragment.view.*

class SliderFirstScreenFragment : Fragment() {

    private val viewModel: SlideViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.slider_first_fragment, container, false)

        setListenersForEditText(view)
        setObserver(view)

        return view
    }

    private fun setListenersForEditText(view: View) {
        view.firstNameEditText.addTextChangedListener {
            val firstName = it?.toString()
            viewModel.onFirstFragmentFirstNameChanged(firstName)
        }
        view.lastNameEditText.addTextChangedListener {
            val lastName = it?.toString()
            viewModel.onFirstFragmentLastNameChanged(lastName)
        }
    }

    private fun setObserver(view: View){
        viewModel.slideState.observe(viewLifecycleOwner, Observer{
            when(it){
                is SlideState.FillTheBlanks -> {
                    view.firstNameEditText.setText(it.resume.firstName)
                    view.lastNameEditText.setText(it.resume.lastName)
                }
            }
        })
    }

}