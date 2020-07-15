package com.example.cleanarchitecture.feature.resume.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleanarchitecture.core.ExecuteCondition
import com.example.cleanarchitecture.feature.resume.data.entity.Resume
import com.example.cleanarchitecture.feature.resume.domain.DeleteResumeUseCase
import com.example.cleanarchitecture.feature.resume.domain.RetrieveResumeUseCase

class ProfileViewModel(
    private val retrieveResumeUseCase: RetrieveResumeUseCase,
    private val deleteResumeUseCase: DeleteResumeUseCase
): ViewModel() {

    private val _showState = MutableLiveData<ShowState>()
    val showState: LiveData<ShowState> get() = _showState

    init {
        val resume = retrieveResumeUseCase()
        _showState.value = if (resume != null) ShowState.ShowResumeEvent(
            resume
        ) else ShowState.ToSlideActivityEvent()
    }

    fun onEditBtnClicked(){
        _showState.value =
            ShowState.ToSlideActivityEvent(
                true
            )
    }

    fun onDeleteBtnClicked(){
        when(val execute = deleteResumeUseCase()){
            is ExecuteCondition.Success -> _showState.value =
                ShowState.ToSlideActivityEvent()
            is ExecuteCondition.Error -> _showState.value =
                ShowState.Error(
                    execute.err.message.toString()
                )
        }
    }

}

sealed class ShowState{
    class ToSlideActivityEvent(val isEditing: Boolean = false): ShowState()
    class Error(val message: String): ShowState()
    class ShowResumeEvent(val resume: Resume): ShowState()
}