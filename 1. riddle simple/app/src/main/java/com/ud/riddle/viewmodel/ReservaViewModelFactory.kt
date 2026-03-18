package com.ud.riddle.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ud.riddle.RepositorioCRUD

class ReservaViewModelFactory(
    private val repositorioCRUD: RepositorioCRUD
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReservaViewModel(repositorioCRUD) as T
    }
}