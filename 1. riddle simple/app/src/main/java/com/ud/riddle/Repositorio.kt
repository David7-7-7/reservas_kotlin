package com.ud.riddle

import kotlinx.coroutines.flow.Flow

class Repositorio(private val dao: DAOReserva) {
    fun getReservas(): Flow<List<Reserva>> = dao.getAll()
    suspend fun saveReserva(r: Reserva) = dao.insert(r)
    suspend fun deleteReserva(r: Reserva) = dao.delete(r)
}