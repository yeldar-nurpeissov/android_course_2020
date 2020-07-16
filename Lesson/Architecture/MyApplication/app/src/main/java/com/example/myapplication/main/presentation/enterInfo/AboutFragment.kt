package com.example.myapplication.main.presentation.enterInfo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.main.domain.entity.User
import com.example.myapplication.main.presentation.detailOfUser.DetailActivity
import kotlinx.android.synthetic.main.fragment_about.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class AboutFragment : Fragment() {

    private val viewModel: AboutViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)


        initListener()
        observeViewModel()
    }


    private fun initListener(){
        nextAboutButton.setOnClickListener {
            val about = aboutEditText.text.toString()

            viewModel.onNextButtonClicked(about)
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
        aboutEditText.setText(user.about)
    }

    private fun goToNext(){
        requireActivity().startActivity(Intent(activity, DetailActivity::class.java))
        requireActivity().finish()
    }

    private fun showToast(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


}