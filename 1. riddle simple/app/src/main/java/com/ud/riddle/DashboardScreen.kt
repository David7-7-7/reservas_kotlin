package com.ud.riddle

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DashboardScreen(viewModel: DashboardViewModel) {
    val reservas by viewModel.reservas.collectAsStateWithLifecycle()


    LazyColumn {
        items(reservas) { reserva ->
            Text(reserva.cliente)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            , horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Golf Club",
            fontSize = 26.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            DashboardCard("Reservas Hoy", "12", Color(0xFF4CAF50))
            DashboardCard("Canchas Ocupadas", "5", Color(0xFF8BC34A))
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            DashboardCard("Reservas Activas", "8", Color(0xFF66BB6A))
            DashboardCard("Finalizadas", "4", Color(0xFF9E9E9E))
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Próximas Reservas",
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(reservas) { reserva ->
                ReservaItem(reserva)
            }
        }

        Button(
            onClick = {viewModel.irA(Screen.ListReservations)},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Nueva Reserva")
        }

        Button(
            onClick = {viewModel.irADashboard()},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Listado Reservas")
        }
    }
}

@Composable
fun DashboardCard(titulo: String, valor: String, color: Color) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(100.dp),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = titulo,
                color = Color.White
            )

            Text(
                text = valor,
                color = Color.White,
                fontSize = 28.sp
            )
        }
    }
}

@Composable
fun ReservaItem(reserva: Reserva) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {

        Row(
            modifier = Modifier
                .padding(12.dp)
        ) {

            Text(
                text = "${reserva.cliente} - ${reserva.hora} - ${reserva.cancha}"
            )
        }
    }
}