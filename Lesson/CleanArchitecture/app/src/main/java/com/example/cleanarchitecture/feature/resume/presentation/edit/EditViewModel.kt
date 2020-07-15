package com.example.cleanarchitecture.feature.resume.presentation.edit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleanarchitecture.core.ExecuteCondition
import com.example.cleanarchitecture.feature.resume.data.entity.Resume
import com.example.cleanarchitecture.feature.resume.domain.RetrieveResumeUseCase
import com.example.cleanarchitecture.feature.resume.domain.SaveResumeUseCase

class SlideViewModel(
    private val saveResumeUseCase: SaveResumeUseCase,
    private val retrieveResumeUseCase: RetrieveResumeUseCase,
    private val isEditing: Boolean
) : ViewModel(){

    val slideState: LiveData<SlideState> get() = _slideState
    private val _slideState = MutableLiveData<SlideState>()

    private var resume: Resume

    init {
        val res = retrieveResumeUseCase()

        if (res != null && !isEditing) {
            resume = res
            _slideState.value =
                SlideState.NextActivity
        }else{
            resume = res ?: Resume()
            _slideState.value =
                SlideState.FillTheBlanks(
                    resume
                )
        }
    }

    fun onFirstFragmentFirstNameChanged(firstName: String?) {
        resume.firstName = firstName ?: ""
    }
    fun onFirstFragmentLastNameChanged(lastName: String?) {
        resume.lastName = lastName ?: ""
    }

    fun onSecondFragmentBirthChanged(birth: String?) {
        resume.birthday = birth ?: ""
    }

    fun onSecondFragmentHeightChanged(height: Int?) {
        resume.height = height ?: 0
    }

    fun onSecondFragmentWeightChanged(weight: Int?) {
        resume.weight = weight ?: 0
    }

    fun onThirdFragmentAboutChanged(about: String?) {
        resume.aboutMe = about ?: ""
    }

    fun onSubmitClicked() {

        Log.i("SlideViewModel", "$resume")

        if (resume.firstName.isBlank()){
            _slideState.value =
                SlideState.ValidationError(
                    "Fill First Name"
                )
            return
        }
        if (resume.lastName.isBlank()){
            _slideState.value =
                SlideState.ValidationError(
                    "Fill Last Name"
                )
            return
        }
        if (resume.birthday.isBlank()){
            _slideState.value =
                SlideState.ValidationError(
                    "Fill Birthday"
                )
            return
        }
        if (resume.height <= 0){
            _slideState.value =
                SlideState.ValidationError(
                    "Fill Height"
                )
            return
        }
        if (resume.weight <= 0){
            _slideState.value =
                SlideState.ValidationError(
                    "Fill Weight"
                )
            return
        }
        if (resume.aboutMe.isBlank()){
            _slideState.value =
                SlideState.ValidationError(
                    "Fill About Yourself"
                )
            return
        }

        when(val execution = saveResumeUseCase(resume)){
            is ExecuteCondition.Success -> _slideState.value =
                SlideState.NextActivity
            is ExecuteCondition.Error -> _slideState.value =
                SlideState.Error(
                    execution.err.message!!
                )
        }
    }
}

sealed class SlideState{
    class Error(val err: String) : SlideState()
    class ValidationError(val err: String): SlideState()
    class FillTheBlanks(val resume: Resume): SlideState()
    object NextActivity: SlideState()
}