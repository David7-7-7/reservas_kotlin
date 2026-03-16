package com.ud.riddle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn

class DashboardViewModel (private val repository: Repositorio) : ViewModel() {
    private val _pantallaActual = MutableStateFlow<Screen>(Screen.Main)
    val pantallaActual: StateFlow<Screen> = _pantallaActual.asStateFlow()
    val reservas: StateFlow<List<Reserva>> = repository.getReservas()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun irA(pantalla: Screen) {
        _pantallaActual.value = pantalla
    }

    fun irADashboard() {
        _pantallaActual.value = Screen.Main
    }
}
