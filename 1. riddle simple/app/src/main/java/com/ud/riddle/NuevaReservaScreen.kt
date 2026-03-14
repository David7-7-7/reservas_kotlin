package com.ud.riddle

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NuevaReservaScreen(viewModel: ReservaViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            , horizontalAlignment =Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(33.dp))

        Text(
            text = "Nueva Reserva",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Nombre del Cliente") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = "12/05/2026",
            onValueChange = {},
            label = { Text("Fecha") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = "10:00 AM",
            onValueChange = {},
            label = { Text("Hora") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = "1",
            onValueChange = {},
            label = { Text("Número de Cancha") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = "4",
            onValueChange = {},
            label = { Text("Cantidad de Jugadores") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = "Activa",
            onValueChange = {},
            label = { Text("Estado") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {

            Button(onClick = {viewModel.volverDashboard()}) {
                Text("Guardar")
            }

            Button(
                onClick = {viewModel.volverDashboard()},
                colors = ButtonDefaults.buttonColors(containerColor = androidx.compose.ui.graphics.Color.Gray)
            ) {
                Text("Cancelar")
            }
        }
    }
}