package hun.portfolio.first.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UserInput(
    modifier: Modifier = Modifier,
    onMessageSent: (String) -> Unit
) {
    var textState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var textFieldFocusState by remember { mutableStateOf(false) }

    UserInputText(
        onTextChanged = { textState = it },
        textFieldValue = textState,
        keyboardShown = true,
        onTextFieldFocused = { focused ->
            textFieldFocusState = focused
        },
        onMessageSent = {},
        focusState = textFieldFocusState
    )
}

@Preview
@Composable
fun UserInputPreview() {
    UserInput(onMessageSent = {})
}

@Composable
private fun UserInputText(
    keyboardType: KeyboardType = KeyboardType.Text,
    onTextChanged: (TextFieldValue) -> Unit,
    textFieldValue: TextFieldValue,
    keyboardShown: Boolean,
    onTextFieldFocused: (Boolean) -> Unit,
    onMessageSent: (String) -> Unit,
    focusState: Boolean
) {
    Row(
        modifier = Modifier
            .background(color = Color.Gray)
            .fillMaxWidth()
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.weight(1f)) {
            UserInputTextField(
                textFieldValue,
                onTextChanged,
                onTextFieldFocused,
                keyboardType,
                focusState,
                onMessageSent,
                Modifier.fillMaxWidth()
            )
        }
        Button(
            modifier = Modifier,
            onClick = {}
        ) {
            Text("Send")
        }
    }
}

@Composable
private fun BoxScope.UserInputTextField(
    textFieldValue: TextFieldValue,
    onTextChanged: (TextFieldValue) -> Unit,
    onTextFieldFocused: (Boolean) -> Unit,
    keyboardType: KeyboardType,
    focusState: Boolean,
    onMessageSent: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var lastFocusState by remember { mutableStateOf(false) }

    BasicTextField(
        value = textFieldValue,
        onValueChange = { onTextChanged(it) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        singleLine = true,
        modifier = modifier
            .align(Alignment.CenterStart)
            .onFocusChanged { state ->
                if (lastFocusState != state.isFocused) {
                    onTextFieldFocused(state.isFocused)
                }

                lastFocusState = state.isFocused
            }
    )

    if (textFieldValue.text.isEmpty() && !focusState) {
        Text(
            text = "Enter a message",
            modifier = modifier.align(Alignment.CenterStart)
        )
    }
}
