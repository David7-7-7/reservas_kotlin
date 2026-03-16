package com.ud.riddle

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle


class MainActivity : ComponentActivity() {

    private val dashViewModel: DashboardViewModel by viewModels()
    private val newResViewModel: ModeloCreaReserva by viewModels()
    private val listaViewModel: ModeloCreaReserva by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val pantalla by dashViewModel.pantallaActual.collectAsStateWithLifecycle()

            MaterialTheme {
                when(pantalla){
                    Screen.Main -> DashboardScreen(dashViewModel)
                    Screen.NewReservation -> NuevaReservaScreen(newResViewModel)
                    else -> DashboardScreen(dashViewModel)
                }
            }
        }
    }
}