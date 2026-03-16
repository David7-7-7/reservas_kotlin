package com.ud.riddle

import androidx.room.Dao
import kotlinx.coroutines.flow.Flow
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DAOReserva {
    @Query("SELECT * FROM reservas ORDER BY fecha ASC")
    fun getAll(): Flow<List<Reserva>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reserva: Reserva)

    @Delete
    suspend fun delete(reserva: Reserva)
}