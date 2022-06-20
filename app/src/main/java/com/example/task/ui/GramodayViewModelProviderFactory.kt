package com.example.task.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.task.repository.GramodayRepository

class GramodayViewModelProviderFactory(
    val repository: GramodayRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GramodayViewModel(repository) as T
    }
}