package com.ud.riddle

sealed class EstadoCreacionReserva {
    object Idle : EstadoCreacionReserva()
    object Loading : EstadoCreacionReserva()
    object Success : EstadoCreacionReserva()
    data class Error(val message: String) : EstadoCreacionReserva()
}