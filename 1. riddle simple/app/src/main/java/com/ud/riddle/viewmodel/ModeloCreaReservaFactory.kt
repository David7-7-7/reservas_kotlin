package com.ud.riddle.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ud.riddle.RepositorioCRUD

class ModeloCreaReservaFactory(
    private val repositorioCRUD: RepositorioCRUD
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ModeloCreaReserva::class.java)) {
            return ModeloCreaReserva(repositorioCRUD) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}