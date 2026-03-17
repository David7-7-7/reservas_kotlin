package com.ud.riddle

import com.ud.riddle.data.local.DAOReserva
import com.ud.riddle.data.local.Reserva
import kotlinx.coroutines.flow.Flow

class Repositorio(private val dao: DAOReserva) {

    suspend fun existeReserva(cancha: String, fecha: String, hora: String) =
        dao.existeReserva(cancha, fecha, hora)
    fun getReservas(): Flow<List<Reserva>> = dao.getAll()
    suspend fun saveReserva(r: Reserva) = dao.insert(r)
    suspend fun deleteReserva(r: Reserva) = dao.delete(r)
    suspend fun existeReserva(r: Reserva) = dao.delete(r)
    fun buscar(nombre: String) = dao.buscarPorCliente(nombre)
}
