package org.example.snipsnip.starters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.example.snipsnip.ui.EditingBox
import org.example.snipsnip.ui.SnippetList

class UIStarter {
    fun UIStarter() = application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Scrollbars",
            state = rememberWindowState(width = 1280.dp, height = 720.dp, placement = WindowPlacement.Floating),
        ) {
            MaterialTheme {
                mainLayout()
            }
        }
    }

    @Composable
    fun mainLayout() {
        Row(Modifier.fillMaxSize()) {
            Column(
                Modifier.weight(1f)
                    .fillMaxHeight()
                    .background(Color.LightGray)
                    .padding(8.dp)
            ) {

                Row(Modifier.height(600.dp), verticalAlignment = Alignment.CenterVertically) {
                    SnippetList()
                }

            }

            Column(
                Modifier.weight(1.5f)
                    .fillMaxHeight()
                    .background(Color.White)
                    .padding(8.dp)
            ) {

                Row(Modifier.height(300.dp), verticalAlignment = Alignment.CenterVertically) {
                    EditingBox(false)
                }

                Row(Modifier.padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                    EditingBox(true)
                }

            }

        }
    }
}