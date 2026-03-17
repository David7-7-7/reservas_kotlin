package com.ud.riddle.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ud.riddle.Repositorio

class ReservaViewModelFactory(
    private val repositorio: Repositorio
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReservaViewModel(repositorio) as T
    }
}