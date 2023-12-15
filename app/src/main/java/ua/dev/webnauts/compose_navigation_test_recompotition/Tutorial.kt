package ua.dev.webnauts.compose_navigation_test_recompotition

import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties


@Composable
fun Tutorial(onDismiss:()-> Unit) {
    val configuration = LocalConfiguration.current

    AlertDialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        modifier = Modifier.widthIn(max = configuration.screenWidthDp.dp - 20.dp),
        onDismissRequest = { onDismiss() },
        title = {

        },
        text = {

        },
        confirmButton = {

        },
    )
}