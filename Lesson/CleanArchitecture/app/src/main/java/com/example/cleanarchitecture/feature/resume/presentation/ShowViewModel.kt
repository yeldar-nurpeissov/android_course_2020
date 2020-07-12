package com.example.cleanarchitecture.feature.resume.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecture.core.ExecuteCondition
import com.example.cleanarchitecture.feature.resume.data.entity.Resume
import com.example.cleanarchitecture.feature.resume.domain.ResumeUseCase
import com.example.cleanarchitecture.feature.resume.domain.ShowUseCase

class ShowViewModel(
    private val useCase: ShowUseCase
): ViewModel() {

    private val _showState = MutableLiveData<ShowState>()
    val showState: LiveData<ShowState> get() = _showState

    init {
        val resume = useCase.retrieve()
        _showState.value = if (resume != null) ShowState.ShowResumeEvent(resume) else ShowState.ToSlideActivityEvent()
    }

    fun onEditBtnClicked(){
        _showState.value = ShowState.ToSlideActivityEvent(true)
    }

    fun onDeleteBtnClicked(){
        when(val execute = useCase.delete()){
            is ExecuteCondition.Success -> _showState.value = ShowState.ToSlideActivityEvent()
            is ExecuteCondition.Error -> _showState.value = ShowState.Error(execute.err.message.toString())
        }
    }

}

class ShowViewModelFactory(
    private val useCase: ShowUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShowViewModel(useCase) as T
    }

}

sealed class ShowState{
    class ToSlideActivityEvent(val isEditing: Boolean = false): ShowState()
    class Error(val message: String): ShowState()
    class ShowResumeEvent(val resume: Resume): ShowState()
}