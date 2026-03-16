package com.ud.riddle

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
class ModeloCreaReserva (private val repositorio: Repositorio) : ViewModel() {
    private val _pantallaActual = MutableStateFlow("dashboard")
    val pantallaActual: StateFlow<String> = _pantallaActual
    private val _uiState = MutableStateFlow<EstadoCreacionReserva>(EstadoCreacionReserva.Idle)
    val uiState: StateFlow<EstadoCreacionReserva> = _uiState.asStateFlow()
    var nombre by mutableStateOf("")
    var telefono by mutableStateOf("")
    var fecha by mutableStateOf(LocalDate.now().toString())
    var hora by mutableStateOf(LocalTime.now().toString())
    var cancha by mutableStateOf("1")
    var cantJugadores by mutableStateOf("")
    var estado by mutableStateOf("Active")

    fun creaReserva() {
        viewModelScope.launch {
            repositorio.saveReserva(
                Reserva(
                    cliente = nombre,
                    telefono = telefono,
                    fecha = fecha,
                    hora = hora,
                    cancha = cancha,
                    cantJugadores = cantJugadores,
                    estado = estado
                )
            )
            volverDashboard()
        }
    }

    fun volverDashboard() {
        _pantallaActual.value = "dashboard"
    }

}