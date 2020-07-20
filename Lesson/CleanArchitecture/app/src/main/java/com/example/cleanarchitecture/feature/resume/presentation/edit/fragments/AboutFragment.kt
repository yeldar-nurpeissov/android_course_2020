package com.example.cleanarchitecture.feature.resume.presentation.edit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.feature.resume.presentation.edit.SlideState
import com.example.cleanarchitecture.feature.resume.presentation.edit.EditViewModel
import kotlinx.android.synthetic.main.slider_third_fragment.view.*

class AboutFragment : Fragment() {

    private val viewModel: EditViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.slider_third_fragment, container, false)

        setListenersForEditText(view)
        setListenerForSubmitBtn(view)
        setObserver(view)

        return view
    }

    private fun setListenersForEditText(view: View) {
        view.aboutYourselfEditText.addTextChangedListener {
            val about = it?.toString()
            viewModel.onThirdFragmentAboutChanged(about)
        }
    }

    private fun setObserver(view: View){
        viewModel.slideState.observe(viewLifecycleOwner, Observer{
            when(it){
                is SlideState.FillTheBlanks -> {
                    view.aboutYourselfEditText.setText(it.resume.aboutMe)
                }
            }
        })
    }

    private fun setListenerForSubmitBtn(view: View) {
        view.sliderSubmitBtn.setOnClickListener {
            viewModel.onSubmitClicked()
        }
    }
}