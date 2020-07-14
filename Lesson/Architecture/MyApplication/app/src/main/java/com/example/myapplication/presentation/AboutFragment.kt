package com.example.myapplication.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.core.DependencyInjection
import com.example.myapplication.data.entity.User
import com.example.myapplication.presentation.viewmodel.AboutViewModel
import com.example.myapplication.presentation.viewmodel.DetailViewModel
import com.example.myapplication.presentation.viewmodel.SetUIState
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_detail.*

class AboutFragment : Fragment() {
    private var di: DependencyInjection?= null

    private val viewModel: AboutViewModel by lazy {
        di!!.getAboutViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        di = DependencyInjection.getInstance(context!!)

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