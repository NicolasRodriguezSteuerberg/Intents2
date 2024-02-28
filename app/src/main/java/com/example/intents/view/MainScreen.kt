package com.example.intents.view

import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.intents.Data
import com.example.intents.R
import com.example.intents.SecondActivity
import kotlin.random.Random

@Composable
fun MostrarInterfaz(navController: NavController){
    var resultadoRecibido by remember { mutableStateOf(0) }
    var numero1 by remember { mutableStateOf(0) }
    var numero2 by remember { mutableStateOf(0) }
    val contexto = LocalContext.current

    // Este es el launcher que se encarga de lanzar la actividad SecondActivity y recibir el resultado de la suma
    val resultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result -> // result es el resultado de la actividad SecondActivity
        val data: Intent? = result.data
        val resultado = data?.getIntExtra("resultado", 0)
        if (resultado != null) {
            resultadoRecibido = resultado
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CuadroTexto(resultadoRecibido, numero1, numero2)
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = if (Data.uri.value != null) rememberImagePainter(Data.uri.value.toString()) else painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Descripción de la imagen",
            modifier = Modifier
                .size(100.dp) // Ajusta el tamaño de la imagen según tus necesidades
        )
        Button(
            onClick = {
                navController.navigate("camera")
            }
        ) {
            Text(text = "Camara")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val intent = Intent(contexto, SecondActivity::class.java)
                numero1 = Random.nextInt(1,100)// [1,100)
                numero2 = Random.nextInt(1,100)
                intent.putExtra("numero1", numero1)
                intent.putExtra("numero2", numero2)
                resultLauncher.launch(intent)
            }
        ) {
            Text(text = "suma")
        }

    }
}


@Composable
fun CuadroTexto(resultado: Int, numero1: Int, numero2: Int){
    var text by remember { mutableStateOf("") }
    if (resultado != 0) {
        if(resultado == (numero1 + numero2)) {
            text = "El resultado que mandaste es el correcto: $resultado"
        } else{
            text = "Resultado recibido: $resultado, el resultado correcto era: ${numero1 + numero2}"
        }
    }
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