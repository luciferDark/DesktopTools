import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
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
    val windowState = rememberWindowState()
    val dpSize : DpSize  = DpSize(Window_Width, Window_Heigth)
    windowState.size = dpSize
    Window(
        onCloseRequest = ::exitApplication,
        title = AppTilte,
        icon = painterResource(AppIcon),
        resizable = false,
        state = windowState
    ) {
        App()
    }
}
