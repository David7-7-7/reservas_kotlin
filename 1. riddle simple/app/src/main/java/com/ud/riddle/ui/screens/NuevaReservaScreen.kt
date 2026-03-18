package com.ud.riddle.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ud.riddle.estados.EstadoCreacionReserva
import com.ud.riddle.viewmodel.ModeloCreaReserva
import com.ud.riddle.viewmodel.DashboardViewModel
import androidx.compose.material3.OutlinedTextField

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NuevaReservaScreen(
    viewModel: ModeloCreaReserva,
    dashViewModel: DashboardViewModel
) {

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
        , horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(33.dp))

        Text(
            text = "Nueva Reserva",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.nombre,
            onValueChange = {viewModel.nombre = it},
            label = { Text("Nombre del Cliente") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = viewModel.telefono.toString(),
            onValueChange = {
                viewModel.telefono = it.toIntOrNull() ?: 0
            },
            label = { Text("Teléfono") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField( value = viewModel.fecha,
            onValueChange = {viewModel.fecha = it},
            label = { Text("Fecha") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField( value = viewModel.hora,
            onValueChange = {viewModel.hora = it},
            label = { Text("Hora") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = viewModel.cancha,
            onValueChange = {viewModel.cancha = it},
            label = { Text("Cancha") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = viewModel.cantJugadores,
            onValueChange = {viewModel.cantJugadores = it},
            label = { Text("Cantidad de Jugadores") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = viewModel.estado,
            onValueChange = {viewModel.estado = it},
            label = { Text("Estado") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        when (uiState) {
            is EstadoCreacionReserva.Error -> {
                Text(
                    text = (uiState as EstadoCreacionReserva.Error).message,
                    color = Color.Red
                )
            }
            else -> {}
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {

            Button(onClick = {
                viewModel.creaReserva()
              //  viewModel.limpiarCampos()
            }) {
                Text("Guardar")
            }

            Button(
                onClick = { dashViewModel.irADashboard() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text("Cancelar")
            }

            Button(
                onClick = { dashViewModel.irADashboard() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text("Volver")
            }
        }
    }
}
