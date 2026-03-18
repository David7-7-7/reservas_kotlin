package com.ud.riddle.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="reservas") // Diseño de la tabla
data class Reserva(
    @PrimaryKey(autoGenerate=true) val id: Int = 0,
    val fecha: String,
    val hora: String,
    val telefono: Int,
    val cancha: String,
    val cliente: String,
    val cantJugadores: String,
    val estado: String
)