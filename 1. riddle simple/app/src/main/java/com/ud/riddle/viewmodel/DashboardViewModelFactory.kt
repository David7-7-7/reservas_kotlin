package com.ud.riddle.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ud.riddle.RepositorioCRUD

class DashboardViewModelFactory(
    private val repositorioCRUD: RepositorioCRUD
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(repositorioCRUD) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}