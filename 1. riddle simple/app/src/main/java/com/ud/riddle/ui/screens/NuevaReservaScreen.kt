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

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NuevaReservaScreen(
    viewModel: ModeloCreaReserva,
    dashViewModel: DashboardViewModel
) {

    val uiState by viewModel.uiState.collectAsState()

    var expanded by remember { mutableStateOf(false) }
    val canchas = (1..10).toList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Nueva Reserva",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.nombre,
            onValueChange = { viewModel.nombre = it },
            label = { Text("Nombre del Cliente") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = viewModel.telefono,
            onValueChange = { viewModel.telefono = it },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = viewModel.fecha,
            onValueChange = { viewModel.fecha = it },
            label = { Text("Fecha") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = viewModel.hora,
            onValueChange = { input ->
                if (input.length <= 5) {
                    viewModel.hora = input
                }
            },
            label = { Text("Hora (HH:mm)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {

            OutlinedTextField(
                value = "Cancha ${viewModel.cancha}",
                onValueChange = {},
                readOnly = true,
                label = { Text("Número de Cancha") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                canchas.forEach { numero ->
                    DropdownMenuItem(
                        text = { Text("Cancha $numero") },
                        onClick = {
                            viewModel.cancha = numero.toString()
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = viewModel.cantJugadores,
            onValueChange = { viewModel.cantJugadores = it },
            label = { Text("Cantidad de Jugadores") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = viewModel.estado,
            onValueChange = { viewModel.estado = it },
            label = { Text("Estado (Activa / Cancelada / Finalizada)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (uiState is EstadoCreacionReserva.Error) {
            Text(
                text = (uiState as EstadoCreacionReserva.Error).message,
                color = Color.Red
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {

            Button(onClick = {
                //viewModel.limpiarCampos()
                viewModel.creaReserva()
                dashViewModel.irADashboard()
            }) {
                Text("Guardar")
            }

            Button(
                onClick = { dashViewModel.irADashboard() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text("Cancelar")
            }
        }
    }
}