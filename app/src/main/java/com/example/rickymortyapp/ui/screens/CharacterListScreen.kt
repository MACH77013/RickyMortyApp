package com.example.rickymortyapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter // Asegúrate de tener esta importación
import com.example.rickymortyapp.data.Character
import com.example.rickymortyapp.data.RetrofitInstance
import kotlinx.coroutines.launch

@Composable
fun CharacterListScreen(navController: NavController) {
    val characters = remember { mutableStateListOf<Character>() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val response = RetrofitInstance.api.getCharacters()
            characters.addAll(response.results)
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Personajes R&M", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(bottom = 16.dp))

        LazyColumn {
            items(characters) { character ->
                CharacterCard(character) {
                    navController.navigate("detail/${character.id}")
                }
            }
        }
    }
}

@Composable
fun CharacterCard(character: Character, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Mostrar imagen del personaje
            Image(
                painter = rememberImagePainter(character.image),
                contentDescription = null,
                modifier = Modifier.size(64.dp) // Tamaño de la imagen
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = character.name, style = MaterialTheme.typography.titleMedium)
                Text(text = character.species)
            }
        }
    }
}
