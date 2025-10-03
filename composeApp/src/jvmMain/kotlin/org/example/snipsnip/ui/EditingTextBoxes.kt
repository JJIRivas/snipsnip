package org.example.snipsnip.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.HorizontalScrollbar
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp


@Composable
@Preview
fun EditingBox(isCodeEdit: Boolean){
    var userWrittenText by remember { mutableStateOf(TextFieldValue()) }

    val verticalScroll = rememberScrollState()
    val horizontalScroll = rememberScrollState()

    var containerSize by remember { mutableStateOf(IntSize.Zero) }
    var contentSize by remember { mutableStateOf(IntSize.Zero) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(red = 195, green = 194, blue = 194, alpha = 255))
            .onSizeChanged { containerSize = it }
    ) {
        Box(
            modifier = Modifier
                .verticalScroll(verticalScroll)
                .horizontalScroll(horizontalScroll)
        ) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .onSizeChanged { contentSize = it }
            ) {
                if(isCodeEdit){
                    LineEnumeration(lineCounter = userWrittenText.text.lines().size)
                }

                VariableTextBox(
                    value = userWrittenText,
                    onValueChanged = { userWrittenText = it },
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .wrapContentSize()
                )
            }
        }

        if (contentSize.height > containerSize.height) {
            VerticalScrollbar(
                adapter = rememberScrollbarAdapter(verticalScroll),
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }

        if (contentSize.width > containerSize.width) {
            HorizontalScrollbar(
                adapter = rememberScrollbarAdapter(horizontalScroll),
                modifier = Modifier.align(Alignment.BottomStart)
            )
        }
    }
}

@Composable
fun LineEnumeration(lineCounter: Int) {
    Column(modifier = Modifier.padding(vertical = 1.dp, horizontal = 4.dp)) {
        for(i in 1..lineCounter) {
            Text(
                text = i.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
    }
    }
}

@Composable
fun VariableTextBox(value: TextFieldValue,
                onValueChanged: (TextFieldValue) -> Unit,
                modifier: Modifier = Modifier,
                textStyle: TextStyle = MaterialTheme.typography.bodyMedium) {
    BasicTextField(
        value = value,
        onValueChange = onValueChanged,
        textStyle = textStyle,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp),
        cursorBrush = SolidColor(Color(red = 255, green = 255, blue = 255, alpha = 255)),
    )
}









