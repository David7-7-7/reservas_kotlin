package com.ud.riddle.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Reserva::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun reservaDao(): DAOReserva
}