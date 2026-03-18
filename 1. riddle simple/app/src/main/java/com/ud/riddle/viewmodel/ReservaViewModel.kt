package com.ud.riddle.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ud.riddle.RepositorioCRUD
import com.ud.riddle.Screen
import com.ud.riddle.data.local.Reserva
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ReservaViewModel(private val repositorioCRUD: RepositorioCRUD) : ViewModel() {

    // 🔍 Texto de búsqueda
    var query by mutableStateOf("")
    private val _pantallaActual = MutableStateFlow("dashboard")
    val pantallaActual: StateFlow<String> = _pantallaActual


    // 📋 Lista de reservas dinámica (con búsqueda)
    val reservas = snapshotFlow { query }
        .flatMapLatest {
            if (it.isEmpty()) repositorioCRUD.getReservas()
            else repositorioCRUD.buscar(it)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    // 🗑 Eliminar reserva
    fun eliminar(reserva: Reserva) {
        viewModelScope.launch {
            repositorioCRUD.deleteReserva(reserva)
        }
    }




}