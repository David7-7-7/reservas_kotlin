package com.ud.riddle.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DAOReserva {
    @Query("SELECT * FROM reservas ORDER BY fecha ASC")
    fun getAll(): Flow<List<Reserva>>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(reserva: Reserva)

    @Delete
    suspend fun delete(reserva: Reserva)

    @Query("""
    SELECT * FROM reservas 
    WHERE cancha = :cancha 
    AND fecha = :fecha 
    AND hora = :hora 
    AND estado = 'Active'
    LIMIT 1
    """)
    suspend fun existeReserva(cancha: String, fecha: String, hora: String): Reserva?

    //Busqueda por cliente
    @Query("SELECT * FROM reservas WHERE cliente LIKE '%' || :nombre || '%'")
    fun buscarPorCliente(nombre: String): Flow<List<Reserva>>


}