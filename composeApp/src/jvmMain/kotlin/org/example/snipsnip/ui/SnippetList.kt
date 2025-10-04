package org.example.snipsnip.ui

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun SnippetList(){
    Box(Modifier.fillMaxSize()
        .background(Color.Transparent)
        .padding(8.dp)) {
        val state = rememberLazyListState()

        LazyColumn(Modifier.fillMaxSize().padding(8.dp), state = state) {
            items(1000){x -> TextBox("Snippet $x")
            Spacer(modifier = Modifier.height(8.dp))
            }
        }

        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            adapter = rememberScrollbarAdapter(state)
        )
    }
}

@Composable
fun TextBox(text: String = "Item") {
    Box(
        modifier = Modifier.height(65.dp)
            .fillMaxWidth()
            .background(color = Color(0, 0, 0, 20))
            .padding(start = 5.dp)
            .clickable(onClick = {}),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(text = text)
    }
}

@Composable
fun SnippetObjectFormat(){
    var currentDate by remember { mutableStateOf(TextFieldValue()) }
    var editedDate by remember { mutableStateOf(TextFieldValue()) }
    val name by remember { mutableStateOf(TextFieldValue()) }
    val language by remember { mutableStateOf(TextFieldValue()) }
    val tagsUsed by remember { mutableStateOf(TextFieldValue()) }
}