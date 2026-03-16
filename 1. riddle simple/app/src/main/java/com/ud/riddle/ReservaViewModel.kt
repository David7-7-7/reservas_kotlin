package com.ud.riddle

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ReservaViewModel : ViewModel() {

    private val _pantallaActual = MutableStateFlow("dashboard")
    val pantallaActual: StateFlow<String> = _pantallaActual
    //StateFlow sirve para guardar y emitir estados que pueden cambiar en el tiempo,
    // y que la interfaz (Compose) se actualice automáticamente cuando cambian.


}