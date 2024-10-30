package com.example.rickymortyapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import androidx.navigation.NavController
import com.example.rickymortyapp.data.Character
import com.example.rickymortyapp.data.RetrofitInstance
import kotlinx.coroutines.launch

@Composable
fun CharacterDetailScreen(navController: NavController, characterId: Int) {
    var character by remember { mutableStateOf<Character?>(null) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(characterId) {
        coroutineScope.launch {
            character = RetrofitInstance.api.getCharacter(characterId)
        }
    }

    character?.let { char ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberImagePainter(char.image),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 16.dp)
            )

            Text(
                text = char.name,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    Text(text = "Estado: ${char.status}", style = MaterialTheme.typography.titleMedium)

                    Text(text = "Especie: ${char.species}", style = MaterialTheme.typography.titleMedium)

                    Text(text = "Género: ${char.gender}", style = MaterialTheme.typography.titleMedium)

                    Text(text = "Ubicación: ${char.location.name}", style = MaterialTheme.typography.titleMedium)

                    Spacer(modifier = Modifier.height(8.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Detalles adicionales", style = MaterialTheme.typography.titleSmall)
                    Text(text = "Dimensiones: ${char.dimension}", style = MaterialTheme.typography.titleMedium)
                    Text(text = "Origen: ${char.origin.name}", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    } ?: run {
        Text(text = "Cargando...")
    }
}
