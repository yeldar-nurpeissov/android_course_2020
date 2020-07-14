package com.example.myapplication.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.core.DependencyInjection
import com.example.myapplication.data.entity.User
import com.example.myapplication.presentation.viewmodel.NameViewModel
import com.example.myapplication.presentation.viewmodel.SetUIState
import kotlinx.android.synthetic.main.fragment_name.*


class NameFragment : Fragment() {
    private var di: DependencyInjection?=null

    private val viewModel: NameViewModel by lazy {
        di!!.getNameViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_name, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        di = DependencyInjection.getInstance(context!!)

        initListener()
        observeViewModel()
    }

    private fun initListener(){
        nextNameButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val surname = surnamEditText.text.toString()

            viewModel.onNextButtonClicked(name, surname)
        }
    }

    private fun observeViewModel(){
        viewModel.authState.observe(this, Observer {state ->
            when (state){
                is SetUIState.Error -> showToast(state.throwable.message)
                is SetUIState.ValidationError -> showToast(state.message)
                is SetUIState.Success -> goToNext()
                is SetUIState.UserEdit -> populate(state.user)
            }
        })
    }

    private fun populate(user: User){
        nameEditText.setText(user.name)
        surnamEditText.setText(user.surname)
    }

    private fun goToNext(){
        requireActivity().findViewById<ViewPager2>(R.id.viewPager2).currentItem = 1
    }

    private fun showToast(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


}