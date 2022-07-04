package com.devaruluis.loanscompose.ui

import Screen
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Icon
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Menu(
    allScreens: List<Screen>,
    onTabSelected: (Screen) -> Unit,
    currentScreen: Screen,
    closeMenu: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        TextButton(onClick = closeMenu, modifier = Modifier.padding(top = 5.dp)) {
            Icon(
                Icons.Default.Close,
                contentDescription = "Close",
                modifier = Modifier.size(32.dp),
                tint = Color.Black
            )
        }
    }
    Column(modifier = Modifier
        .selectableGroup()
        .fillMaxWidth()
        .padding(5.dp)) {
        allScreens.forEach {
            MenuButton(
                it,
                selected = it == currentScreen,
                onClickAction = { onTabSelected(it) })
        }
    }
}