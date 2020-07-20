package com.example.myapplication.main.presentation.enterInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.main.domain.entity.User
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {


    private val viewModel: DetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)


        initListener()
        observeViewModel()
    }

    private fun initListener(){
        nextDetailButton.setOnClickListener {
            val dateOfBirth = dateEditText.text.toString()
            val weight = weightEditText.text.toString()
            val height = heightEditText.text.toString()

            viewModel.onNextButtonClicked(dateOfBirth, weight, height)
        }
    }

    private fun observeViewModel(){
        viewModel.authState.observe(viewLifecycleOwner, Observer {state ->
            when (state){
                is SetUIState.Error -> showToast(state.throwable.message)
                is SetUIState.ValidationError -> showToast(state.message)
                is SetUIState.Success -> goToNext()
                is SetUIState.UserEdit -> populate(state.user)
            }
        })
    }

    private fun populate(user: User){
        dateEditText.setText(user.date)
        weightEditText.setText("${user.weight}")
        heightEditText.setText("${user.height}")
    }

    private fun goToNext(){
        requireActivity().findViewById<ViewPager2>(R.id.viewPager2).currentItem = 2
    }

    private fun showToast(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }



}