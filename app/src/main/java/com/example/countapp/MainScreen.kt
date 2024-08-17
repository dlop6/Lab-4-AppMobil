package com.example.countapp

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.LocalContext

@Composable
fun MainScreen() {
    var receta by remember { mutableStateOf("") }
    var url by remember { mutableStateOf("") }
    val context = LocalContext.current
    val itemList = remember { mutableStateListOf<Recipe>() }

    fun agregarRecetas() {
        if (itemList.any { it.name == receta }) {
            Toast.makeText(context, "Receta ya agregada", Toast.LENGTH_SHORT).show()
            return
        }

        if (receta.isNotBlank() && url.isNotBlank()) {
            itemList.add(Recipe(name = receta, imageUrl = url))
            receta = ""
            url = ""
            Toast.makeText(context, "Recetas agregadas", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Por favor ingrese recetas", Toast.LENGTH_SHORT).show()
        }
    }

    fun eliminarReceta(recipe: Recipe) {
        itemList.remove(recipe)
        Toast.makeText(context, "Receta eliminada", Toast.LENGTH_SHORT).show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Ingrese nombre de receta:")
        TextField(value = receta, onValueChange = { receta = it })
        Text(text = "Ingrese URL de imagen:")
        TextField(value = url, onValueChange = { url = it })
        Button(onClick = { agregarRecetas() }) {
            Text("Agregar receta")
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            thickness = 1.dp
        )

        Text(text = "Lista de recetas:", style = MaterialTheme.typography.titleMedium)

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(itemList) { recipe ->
                RecipeItem(recipe = recipe, onDelete = { eliminarReceta(it) })
            }
        }
    }
}
