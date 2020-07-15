package com.example.cleanarchitecture.feature.resume.presentation.profile

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.core.ResumeFormatter
import com.example.cleanarchitecture.feature.resume.data.entity.Resume
import com.example.cleanarchitecture.feature.resume.presentation.edit.EditActivity
import kotlinx.android.synthetic.main.activity_show.*

class ProfileActivity : AppCompatActivity() {

    private val viewModel: ShowViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        setListeners()
        setObservers()
    }

    private fun setObservers(){
        viewModel.showState.observe(this, Observer {
            when(it){
                is ShowState.ToSlideActivityEvent -> goToSlideActivity(it.isEditing)
                is ShowState.Error -> showToast(it.message)
                is ShowState.ShowResumeEvent -> fillResumeField(it.resume)
            }
        })
    }

    private fun fillResumeField(resume: Resume) {
        wholeInfoTextView.text = ResumeFormatter.stringResume(resume)
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun goToSlideActivity(withEditing: Boolean){
        startActivity(EditActivity.intent(this, withEditing))
        finish()
    }

    private fun showVerificationDialog(clickListener: DialogInterface.OnClickListener) {
        val builder = AlertDialog.Builder(this)
        builder
            .setTitle(R.string.are_you_sure)
            .setPositiveButton("Yes", clickListener)
            .setNegativeButton("No", null)
            .show()
    }

    private fun setListeners() {
        editBtn.setOnClickListener {
            showVerificationDialog(DialogInterface.OnClickListener { p0, p1 ->
                viewModel.onEditBtnClicked()
            })
        }

        deleteBtn.setOnClickListener {
            showVerificationDialog(DialogInterface.OnClickListener { p0, p1 ->
                viewModel.onDeleteBtnClicked()
            })
        }
    }
}
