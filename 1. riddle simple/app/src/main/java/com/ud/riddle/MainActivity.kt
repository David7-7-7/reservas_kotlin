package com.ud.riddle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: ReservaViewModel = viewModel()
            val pantalla = viewModel.pantallaActual.collectAsState()

            MaterialTheme {
                when(pantalla.value){
                    "dashboard" -> DashboardScreen(viewModel)
                    "nuevaReserva" -> NuevaReservaScreen(viewModel)
                    "listadoReservas" -> ListadoReservasScreen(viewModel)
                }
            }
        }
    }
}