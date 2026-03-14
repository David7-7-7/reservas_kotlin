package com.ud.riddle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class ReservaListado(
    val cliente: String,
    val fecha: String,
    val hora: String,
    val cancha: String,
    val estado: String
)

@Composable
fun ListadoReservasScreen(viewModel: ReservaViewModel) {

    val reservas = listOf(
        ReservaListado("Carlos","12/05/2026","10:00 AM","2","Activa"),
        ReservaListado("Ana","12/05/2026","11:30 AM","3","Activa"),
        ReservaListado("Luis","12/05/2026","1:00 PM","1","Finalizada")
    )

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
            value = "",
            onValueChange = {},
            placeholder = { Text("Buscar reserva...") },
            trailingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Buscar")
            },
            modifier = Modifier.fillMaxWidth()
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
                ReservaFila(reserva)
            }
        }
        Button(
            onClick = {viewModel.volverDashboard()},
            colors = ButtonDefaults.buttonColors(containerColor = androidx.compose.ui.graphics.Color.Gray)
        ) {
            Text("Volver")
        }
    }
}

@Composable
fun ReservaFila(reserva: ReservaListado) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(reserva.cliente)
        Text(reserva.fecha)
        Text(reserva.hora)
        Text(reserva.cancha)

        val colorEstado =
            if (reserva.estado == "Activa") Color(0xFF4CAF50) else Color.Red

        Text(
            reserva.estado,
            color = Color.White,
            modifier = Modifier
                .background(colorEstado)
                .padding(4.dp)
        )

        IconButton(onClick = {}) {
            Icon(Icons.Default.Edit, contentDescription = "Editar")
        }
    }
}