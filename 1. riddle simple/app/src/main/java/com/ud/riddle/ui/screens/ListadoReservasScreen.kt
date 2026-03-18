package com.ud.riddle.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ud.riddle.data.local.Reserva
import com.ud.riddle.viewmodel.DashboardViewModel
import com.ud.riddle.viewmodel.ReservaViewModel


@Composable
fun ListadoReservasScreen(viewModel: ReservaViewModel, dashViewModel: DashboardViewModel) {
    val reservas by viewModel.reservas.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Listado de Reservas",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.query,
            onValueChange = { viewModel.query = it },
            placeholder = { Text("Buscar reserva...") },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(reservas) { reserva ->
                ReservaCard(reserva, viewModel)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { dashViewModel.irADashboard() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }

}


@Composable
fun ReservaCard(reserva: Reserva, viewModel: ReservaViewModel) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = MaterialTheme.shapes.medium
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = reserva.cliente,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("📅 ${reserva.fecha}")
                Text("⏰ ${reserva.hora}")
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text("Cancha: ${reserva.cancha}")

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = reserva.estado,
                color = when (reserva.estado) {
                    "Activa" -> Color(0xFF4CAF50)
                    "Cancelada" -> Color.Red
                    else -> Color.Gray
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {

                IconButton(onClick = { viewModel.eliminar(reserva) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                }
            }
        }
    }
}
