package ui

import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import views.ChangeShapeExtendedFloatingActionButton

@Composable
fun UI_Main_TopBar() {
    val text by remember { mutableStateOf("Hello, World!") }
    TopAppBar(
        title = {
            Text(text)
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    print("open then drawer")
                }
            ) {
                Icon(Icons.Filled.Menu, contentDescription = null)
            }
        }
    )
}

@Composable
fun UI_Main_BottomBar() {
    val text by remember { mutableStateOf("Hello, World!") }
    BottomAppBar {
        Text(text)
    }
}

@Composable
fun UI_Main_FloatButton(onClick: ((Boolean) -> Unit)? = null) {
    ChangeShapeExtendedFloatingActionButton(
        text = { Text("悬浮按钮") },
        onClick = {
            println("悬浮按钮 onClick")
            val openDialog = true
            onClick?.invoke(openDialog)
        })
}
