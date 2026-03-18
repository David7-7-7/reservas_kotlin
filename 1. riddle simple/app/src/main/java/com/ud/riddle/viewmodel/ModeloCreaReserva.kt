package com.ud.riddle.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ud.riddle.estados.EstadoCreacionReserva
import com.ud.riddle.RepositorioCRUD
import com.ud.riddle.data.local.Reserva
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
class ModeloCreaReserva (private val repositorioCRUD: RepositorioCRUD) : ViewModel() {

    private val _uiState = MutableStateFlow<EstadoCreacionReserva>(EstadoCreacionReserva.Idle)
    val uiState: StateFlow<EstadoCreacionReserva> = _uiState.asStateFlow()
    var nombre by mutableStateOf("")
    var telefono by mutableIntStateOf(0)
    var fecha by mutableStateOf(LocalDate.now().toString())
    var hora by mutableStateOf(LocalTime.now().toString())
    var cancha by mutableStateOf("1")
    var cantJugadores by mutableStateOf("")
    var estado by mutableStateOf("Active")

    fun creaReserva() {
        viewModelScope.launch {

            val existente = repositorioCRUD.existeReserva(cancha, fecha, hora)

            if (existente != null) {
                _uiState.value = EstadoCreacionReserva.Error("Ya existe una reserva para esa cancha, fecha y hora")
                return@launch
            }
            repositorioCRUD.saveReserva(
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
            _uiState.value = EstadoCreacionReserva.Success
        }
    }

    fun limpiarCampos() {
        nombre = ""
        telefono = 0
        fecha = ""
        hora = ""
        cancha = ""
        cantJugadores = ""
        estado = ""
    }
}