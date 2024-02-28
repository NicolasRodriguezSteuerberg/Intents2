package com.example.intents.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.intents.Data
import com.example.intents.R

@Composable
fun MostrarInterfaz(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CuadroTexto()
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = if (Data.uri.value != null) rememberImagePainter(Data.uri.value.toString()) else painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Descripción de la imagen",
            modifier = Modifier
                .size(100.dp) // Ajusta el tamaño de la imagen según tus necesidades
        )
        Boton(texto = "Boton1", navController = navController)
        Spacer(modifier = Modifier.height(16.dp))
        Boton(texto = "Boton2", navController = navController)

    }
}


@Composable
fun CuadroTexto(){
    var text by remember { mutableStateOf("Esta bien") }
    Text(
        text = text
    )
}


@Composable
fun Boton(texto : String, navController: NavController){
    Button(
        onClick = {
            navController.navigate("camera")
        }
    ) {
        Text(text = texto)
    }
}