package com.ud.riddle.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ud.riddle.Repositorio

class ModeloCreaReservaFactory(
    private val repositorio: Repositorio
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ModeloCreaReserva::class.java)) {
            return ModeloCreaReserva(repositorio) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}