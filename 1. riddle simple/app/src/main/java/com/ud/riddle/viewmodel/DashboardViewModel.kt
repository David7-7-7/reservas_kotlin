package com.ud.riddle.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ud.riddle.RepositorioCRUD
import com.ud.riddle.data.local.Reserva
import com.ud.riddle.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DashboardViewModel (private val repository: RepositorioCRUD) : ViewModel() {
    private val _pantallaActual = MutableStateFlow<Screen>(Screen.Main)
    val pantallaActual: StateFlow<Screen> = _pantallaActual.asStateFlow()
    val reservas: StateFlow<List<Reserva>> = repository.getReservas()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    val canchasOcupadas = reservas.map { lista ->
        lista
            .filter { it.estado == "Active" } // solo activas
            .map { it.cancha }               // obtenemos canchas
            .distinct()                     // evitamos duplicados
            .count()                        // contamos
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        0
    )

    fun irA(pantalla: Screen) {
        _pantallaActual.value = pantalla
    }

    fun irADashboard() {
        _pantallaActual.value = Screen.Main
    }

}