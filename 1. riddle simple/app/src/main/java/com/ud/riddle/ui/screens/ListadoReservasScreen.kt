package com.ud.riddle.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ud.riddle.viewmodel.ModeloCreaReserva
import com.ud.riddle.Screen
import com.ud.riddle.data.local.Reserva
import com.ud.riddle.viewmodel.ReservaViewModel


@Composable
fun ListadoReservasScreen(viewModel: ReservaViewModel) {
    val reservas by viewModel.reservas.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            , horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(30.dp ))

        Text(
            text = "Listado de Reservas",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = viewModel.query,
            onValueChange = { viewModel.query = it },
            placeholder = { Text("Buscar reserva...") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF4CAF50))
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Cliente", color = Color.White)
            Text("Fecha", color = Color.White)
            Text("Hora", color = Color.White)
            Text("Cancha", color = Color.White)
            Text("Estado", color = Color.White)
            Text("Acciones", color = Color.White)
        }

        LazyColumn {
            items(reservas) { reserva ->
                ReservaFila(reserva, viewModel)
            }
        }
        Button(
            onClick = {viewModel.volverDashboard()},
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) {
            Text("Volver a:")
        }
    }
}

@Composable
fun ReservaFila(reserva: Reserva, viewModel: ReservaViewModel) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(reserva.cliente)
        Text(reserva.fecha)
        Text(reserva.hora)
        Text(reserva.cancha)

        Text(reserva.estado)

        IconButton(onClick = { viewModel.eliminar(reserva) }) {
            Icon(Icons.Default.Edit, contentDescription = "Eliminar")
        }
    }
}