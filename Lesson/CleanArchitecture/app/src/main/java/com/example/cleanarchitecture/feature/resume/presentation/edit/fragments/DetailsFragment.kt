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
import kotlinx.android.synthetic.main.slider_second_fragment.view.*
import java.lang.Exception

class DetailsFragment: Fragment() {

    private val viewModel: EditViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.slider_second_fragment, container, false)

        setListenersForEditText(view)
        setObserver(view)

        return view
    }

    private fun setListenersForEditText(view: View) {
        view.birthdayEditText.addTextChangedListener {
            val birth = it?.toString()
            viewModel.onSecondFragmentBirthChanged(birth)
        }
        view.heightEditText.addTextChangedListener {
            val h: Int? = try {
                it?.toString()?.toInt()
            }catch (e: Exception){
                null
            }
            viewModel.onSecondFragmentHeightChanged(h)
        }
        view.weightEditText.addTextChangedListener {
            val w: Int? = try {
                it?.toString()?.toInt()
            }catch (e: Exception){
                null
            }
            viewModel.onSecondFragmentWeightChanged(w)
        }
    }

    private fun setObserver(view: View){
        viewModel.slideState.observe(viewLifecycleOwner, Observer{
            when(it){
                is SlideState.FillTheBlanks -> {
                    view.birthdayEditText.setText(it.resume.birthday)
                    view.heightEditText.setText(it.resume.height.toString())
                    view.weightEditText.setText(it.resume.weight.toString())
                }
            }
        })
    }
}