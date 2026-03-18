package com.ud.riddle.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="reservas")
data class Reserva(
    @PrimaryKey(autoGenerate=true) val id: Int = 0,
    val fecha: String,
    val hora: String,
    val telefono: String,
    val cancha: String,
    val cliente: String,
    val cantJugadores: String,
    var estado: String
)