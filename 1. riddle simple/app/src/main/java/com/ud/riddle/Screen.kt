package com.ud.riddle

sealed class Screen {
    object Main : Screen()
    object NewReservation : Screen()
    object ListReservations : Screen()
}