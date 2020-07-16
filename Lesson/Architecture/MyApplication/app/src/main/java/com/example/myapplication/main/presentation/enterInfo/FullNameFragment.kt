package com.example.myapplication.main.presentation.enterInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.main.domain.entity.User
import kotlinx.android.synthetic.main.fragment_name.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class FullNameFragment : Fragment() {


    private val viewModel: FullNameViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_name, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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