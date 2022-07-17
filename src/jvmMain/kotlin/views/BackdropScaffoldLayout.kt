package views

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropScaffoldState
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BackdropScaffoldLayout(
    scaffoldState : BackdropScaffoldState =  BackdropScaffoldState(BackdropValue.Concealed),
    modifier: Modifier? = Modifier,
    appBar : (@Composable () -> Unit)?,
    backLayerContent : (@Composable () -> Unit)?,
    frontLayerContent : (@Composable () -> Unit)?
) {
    LaunchedEffect(scaffoldState) {
        scaffoldState.conceal()
    }
    BackdropScaffold(
        scaffoldState = scaffoldState,
        peekHeight = 0.dp,
        modifier = Modifier.padding(0.dp, 2.dp, 0.dp, 50.dp),
        appBar = {
            appBar?.invoke()
        },
        backLayerContent = {
            backLayerContent?.invoke()
        },
        frontLayerContent = {
            frontLayerContent?.invoke()
        }
    )
}