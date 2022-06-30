import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.launch
import ui.Ui_Main

@Composable
@Preview
fun App() {
    MaterialTheme(
        colors = lightColors(primary = Color.Black)
    ) {
        Ui_Main()
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = AppTilte,
        icon = painterResource(AppIcon),
    ) {
        App()
    }
}
