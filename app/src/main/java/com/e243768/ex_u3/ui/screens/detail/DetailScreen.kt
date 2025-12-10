package com.e243768.ex_u3.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.e243768.ex_u3.data.model.PokemonDetail
import com.e243768.ex_u3.ui.components.PokeLoader
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    pokemonName: String,
    viewModel: DetailViewModel = hiltViewModel()
) {
    LaunchedEffect(pokemonName) {
        viewModel.loadPokemonDetail(pokemonName)
    }

    val pokemonInfo by viewModel.pokemonInfo.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val isFavorite by viewModel.isFavorite.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Atrás",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.toggleFavorite() },
                containerColor = Color.White,
                contentColor = if (isFavorite) Color.Red else Color.Gray
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorito"
                )
            }
        },
        containerColor = if (isLoading) Color.White else Color(0xFFEF5350)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (isLoading) {
                PokeLoader()
            } else if (pokemonInfo != null) {
                PokemonDetailContent(pokemon = pokemonInfo!!)
            } else {
                Text(
                    text = "No se pudo cargar la información.",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun PokemonDetailContent(pokemon: PokemonDetail) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            AsyncImage(
                model = pokemon.sprites.other.officialArtwork.frontDefault ?: pokemon.sprites.frontDefault,
                contentDescription = pokemon.name,
                modifier = Modifier
                    .size(200.dp)
                    .offset(y = 20.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(Color.White)
                .padding(20.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = pokemon.name.uppercase(),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                pokemon.types.forEach { type ->
                    TypeChip(typeName = type.type.name)
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                PokemonAttribute(value = "${pokemon.weight / 10.0} KG", label = "Peso")
                PokemonAttribute(value = "${pokemon.height / 10.0} M", label = "Altura")
            }

            Text(
                text = "Estadísticas Base",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            pokemon.stats.forEach { stat ->
                StatBar(statName = stat.stat.name, value = stat.baseStat)
            }

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
fun TypeChip(typeName: String) {
    Box(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(parseTypeColor(typeName))
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Text(
            text = typeName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }
}

@Composable
fun StatBar(statName: String, value: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = parseStatName(statName),
            modifier = Modifier.width(100.dp),
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )
        LinearProgressIndicator(
            progress = value / 100f,
            modifier = Modifier
                .weight(1f)
                .height(10.dp)
                .clip(RoundedCornerShape(5.dp)),
            color = if (value > 50) Color(0xFF4CAF50) else Color(0xFFFFC107),
            trackColor = Color(0xFFEEEEEE)
        )
        Text(
            text = "$value",
            modifier = Modifier.width(40.dp).padding(start = 8.dp),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun PokemonAttribute(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text(text = label, color = Color.Gray, fontSize = 12.sp)
    }
}

fun parseTypeColor(type: String): Color {
    return when (type.lowercase()) {
        "fire" -> Color(0xFFF57D31)
        "water" -> Color(0xFF6493EB)
        "grass" -> Color(0xFF74CB48)
        "electric" -> Color(0xFFF9CF30)
        "poison" -> Color(0xFFA43E9E)
        "psychic" -> Color(0xFFFB5584)
        "normal" -> Color(0xFFAAA67F)
        "ground" -> Color(0xFFDEC16B)
        "flying" -> Color(0xFFA891EC)
        "bug" -> Color(0xFFA7B723)
        "rock" -> Color(0xFFB69E31)
        "ghost" -> Color(0xFF70559B)
        "dragon" -> Color(0xFF7037FF)
        "steel" -> Color(0xFFB7B9D0)
        "fairy" -> Color(0xFFE69EAC)
        else -> Color(0xFF75515B)
    }
}

fun parseStatName(stat: String): String {
    return when(stat) {
        "hp" -> "HP"
        "attack" -> "Ataque"
        "defense" -> "Defensa"
        "special-attack" -> "Atq. Esp"
        "special-defense" -> "Def. Esp"
        "speed" -> "Velocidad"
        else -> stat
    }
}