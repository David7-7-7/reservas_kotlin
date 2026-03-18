package com.ud.riddle.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ud.riddle.viewmodel.DashboardViewModel
import com.ud.riddle.data.local.Reserva
import com.ud.riddle.Screen
import com.ud.riddle.viewmodel.ReservaViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DashboardScreen(viewModel: DashboardViewModel) {

    val reservas by viewModel.reservas.collectAsStateWithLifecycle()
    val canchasOcupadas by viewModel.canchasOcupadas.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Golf Club",
            fontSize = 26.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(20.dp))


        Row(modifier = Modifier.fillMaxWidth()) {

            val hoy = java.time.LocalDate.now().toString()
            val reservasHoy = reservas.filter { it.fecha == hoy }

            DashboardCard(
                "Reservas Hoy",
                "${reservasHoy.size}",
                Color(0xFF4CAF50),
                Modifier.weight(1f)
            )


            DashboardCard(
                "Canchas Ocupadas",
                "$canchasOcupadas",
                Color(0xFF8BC34A),
                Modifier.weight(1f)
            )
        }

        Row(modifier = Modifier.fillMaxWidth()) {

            DashboardCard(
                "Reservas Activas",
                "${reservas.count { it.estado == "Activa" }}",
                Color(0xFF66BB6A),
                Modifier.weight(1f)
            )

            DashboardCard(
                "Finalizadas",
                "${reservas.count { it.estado == "Finalizada" }}",
                Color(0xFF9E9E9E),
                Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Próximas Reservas",
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                if (reservas.isEmpty()) {
                    Text("No hay reservas")
                } else {
                    reservas.take(3).forEach { reserva ->
                        ReservaItem(reserva)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { viewModel.irA(Screen.NewReservation) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Nueva Reserva")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button( onClick = {viewModel.irA(Screen.ListReservations)},
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) { Text("Listado Reservas") }
    }
}

@Composable
fun DashboardCard(
    titulo: String,
    valor: String,
    color: Color,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
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

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = reserva.cliente.ifEmpty { "Sin nombre" },
            modifier = Modifier.weight(1f)
        )

        Text(
            text = reserva.hora.take(5),
            modifier = Modifier.weight(1f)
        )

        Text(
            text = "C${reserva.cancha}",
            modifier = Modifier.weight(1f)
        )
    }
}