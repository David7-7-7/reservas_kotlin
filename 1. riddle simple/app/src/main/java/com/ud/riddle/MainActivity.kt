package com.ud.riddle

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.room.Room
import com.ud.riddle.data.local.AppDatabase
import com.ud.riddle.ui.screens.DashboardScreen
import com.ud.riddle.ui.screens.ListadoReservasScreen
import com.ud.riddle.ui.screens.NuevaReservaScreen
import com.ud.riddle.viewmodel.DashboardViewModel
import com.ud.riddle.viewmodel.DashboardViewModelFactory
import com.ud.riddle.viewmodel.ModeloCreaReserva
import com.ud.riddle.viewmodel.ModeloCreaReservaFactory
import com.ud.riddle.viewmodel.ReservaViewModel
import com.ud.riddle.viewmodel.ReservaViewModelFactory

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Crear base de datos
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "riddle-db"
        ).fallbackToDestructiveMigration() .build()


        // Crear repositorio
        val repositorioCRUD = RepositorioCRUD(db.reservaDao())

        // Crear factories
        val dashboardFactory = DashboardViewModelFactory(repositorioCRUD)
        val creaReservaFactory = ModeloCreaReservaFactory(repositorioCRUD)
        val listaReservaFactory = ReservaViewModelFactory(repositorioCRUD)

        // Crear ViewModels correctamente
        val newResViewModel: ModeloCreaReserva by viewModels { creaReservaFactory }
        val dashViewModel: DashboardViewModel by viewModels { dashboardFactory }
        val listaViewModel: ReservaViewModel by viewModels { listaReservaFactory }

        setContent {
            val pantalla by dashViewModel.pantallaActual.collectAsStateWithLifecycle()

            MaterialTheme {
                when (pantalla) {
                    Screen.Main -> DashboardScreen(dashViewModel)
                    Screen.NewReservation -> NuevaReservaScreen(newResViewModel, dashViewModel)
                    Screen.ListReservations -> ListadoReservasScreen(listaViewModel, dashViewModel)
                    else -> DashboardScreen(dashViewModel)
                }
            }
        }
    }
}