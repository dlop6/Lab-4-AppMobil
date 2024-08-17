package com.example.countapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import coil.compose.rememberAsyncImagePainter
import com.example.countapp.Recipe

@Composable
fun RecipeItem(recipe: Recipe, onDelete: (Recipe) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = recipe.name)
        Spacer(modifier = Modifier.width(8.dp))
        ImageScreen(imageUrl = recipe.imageUrl)
        Button(onClick = { onDelete(recipe) }) {
            Text("Eliminar")
        }
    }
}

@Composable
fun ImageScreen(imageUrl: String) {
    val painter = rememberAsyncImagePainter(model = imageUrl)
    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier.size(100.dp)
            .clip(RoundedCornerShape(8.dp))
    )
}
