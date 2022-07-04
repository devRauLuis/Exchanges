package com.devaruluis.loanscompose.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    onMenuClick: () -> Unit
) {
    TopAppBar {
        TextButton(
            onClick = onMenuClick,
            colors = ButtonDefaults.textButtonColors(contentColor = Color.White)

        ) {
            Icon(Icons.Default.Menu, contentDescription = "Menu", modifier = Modifier.size(48.dp))
        }
    }

}