package com.devaruluis.loanscompose.ui.components.form

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RegistryActionButtons(
    handleNewClick: () -> Unit,
    handleSaveClick: () -> Unit,
    handleDeleteClick: () -> Unit
) {

    val buttonModifier = Modifier.defaultMinSize(minWidth = 80.dp)
    Card(modifier = Modifier.fillMaxWidth()) {

    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = handleNewClick,
            modifier = buttonModifier
        ) {
            Text(text = "NUEVO")
        }
        Button(
            onClick = handleSaveClick,
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
            modifier = buttonModifier

        ) {
            Text(text = "GUARDAR")
        }
        OutlinedButton(
            onClick = handleDeleteClick,
            modifier = buttonModifier
        ) {
            Text(text = "ELIMINAR")
        }
    }

}