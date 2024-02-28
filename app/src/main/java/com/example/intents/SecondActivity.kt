package com.example.intents

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.intents.ui.theme.IntentsTheme
import com.example.intents.view.InterfazSuma
import com.example.intents.view.MostrarInterfaz

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntentsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val numero1 = intent.getIntExtra("numero1", 0)
                    val numero2 = intent.getIntExtra("numero2", 0)
                    InterfazSuma(numero1, numero2, this::enviarResultado)
                }
            }
        }
    }

    /**
     * enviarResultado es una función que se encarga de enviar el resultado de la suma a la actividad principal
     * @param resultado es el resultado de la suma
     */
    fun enviarResultado(resultado: Int){
        val returnIntent = Intent()
        returnIntent.putExtra("resultado", resultado)
        // setResult es una función que se encarga de enviar el resultado a la actividad principal
        setResult(RESULT_OK, returnIntent)
        finish()
    }
}