package views

import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DialogView(
    title : String = "",
    text : String = "",
    openDialog: Boolean,
    onDismissRequestCallback: ((Boolean) -> Unit)?,
    onConfirmCallback: ((Boolean) -> Unit)?
) {
    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                onDismissRequestCallback?.invoke(false)
            },
            title = {
                Text(text = title)
            },
            text = {
                Text(
                    text,
                    modifier = Modifier.width(300.dp)
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmCallback?.invoke(false)
                    }
                ) {
                    Text("OK")
                }
            }
        )
    }
}