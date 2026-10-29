package com.example.motivana // Pacote igual ao do seu projeto

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Chama a função que desenha a nossa aplicação
            AppFrasesMotivacionais()
        }
    }
}

@Composable
fun AppFrasesMotivacionais() {
    val contexto = LocalContext.current

    // 1. Criar a lista de frases na memória da aplicação
    val frases = remember {
        mutableStateListOf(
            "Além de não ser fácil, ainda é difícil!",
            "O importante não é vencer todos os dias, mas lutar sempre.",
            "Tudo o que um sonho precisa para ser realizado é alguém que acredite nele."
        )
    }

    // 2. Variáveis para guardar o que aparece no ecrã e o que é digitado
    var fraseSorteada by remember { mutableStateOf("Clique no botão para sortear!") }
    var textoDigitado by remember { mutableStateOf("") }

    // 3. Desenhar o ecrã (Tudo empilhado na vertical)
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Texto da Frase que vai mudar
        Text(
            text = fraseSorteada,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Botão para Sortear
        Button(onClick = {
            val numeroAleatorio = Random.nextInt(frases.size)
            fraseSorteada = frases[numeroAleatorio]
        }) {
            Text("Sortear Frase")
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Caixa de texto para o utilizador escrever a nova frase
        OutlinedTextField(
            value = textoDigitado,
            onValueChange = { textoDigitado = it },
            label = { Text("Adicionar nova frase") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botão para guardar a frase nova
        Button(onClick = {
            if (textoDigitado.isNotBlank()) {
                frases.add(textoDigitado) // Adiciona à lista
                textoDigitado = "" // Limpa a caixa de texto
                Toast.makeText(contexto, "Frase adicionada!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(contexto, "Escreva algo primeiro.", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Adicionar")
        }
    }
}