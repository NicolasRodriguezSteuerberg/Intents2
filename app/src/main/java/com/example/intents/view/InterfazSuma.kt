package com.example.intents.view

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.intents.SecondActivity

val resultado = mutableStateOf("")

/**
 * InterfazSuma es un composable que muestra la interfaz para realizar una suma
 * @param numero1 es el primer número de la suma
 * @param numero2 es el segundo número de la suma
 * @param enviarResultado es una función que se encarga de enviar el resultado de la suma a la actividad principal (proveniente de SecondActivity)
 */
@Composable
fun InterfazSuma(numero1: Int, numero2: Int, enviarResultado: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CuadroSuma(numero1, numero2)
        Resultado()
        SendResultToMain(enviarResultado)
    }
}

@Composable
fun SendResultToMain(enviarResultado: (Int) -> Unit){
    Button(
        onClick = {
            enviarResultado(resultado.value.toInt())
        }
    ) {
        Text(text = "Enviar resultado")
    }
}

@Composable
fun Resultado() {
    OutlinedTextField(
        value = resultado.value,
        onValueChange = {
            resultado.value = it
        },
        label = { Text("Escribe el resultado") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Composable
fun CuadroSuma(numero1: Int, numero2: Int) {
    Text(
        text = "Cuanto es $numero1 + $numero2?"
    )
}