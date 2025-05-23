package hun.portfolio.first.ui.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UserInput(
    onMessageSent: (String) -> Unit
) {
    var textState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var textFieldFocusState by remember { mutableStateOf(false) }

    UserInputText(
        onTextChanged = { textState = it },
        textFieldValue = textState,
        onTextFieldFocused = { focused ->
            textFieldFocusState = focused
        },
        onMessageSent = {
            if (textState.text.isNotBlank()) {
                val text = textState.text
                textState = TextFieldValue("")

                onMessageSent(text)
            }
        },
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
    onTextFieldFocused: (Boolean) -> Unit,
    onMessageSent: (String) -> Unit,
    focusState: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            shape = RoundedCornerShape(48.dp, 48.dp, 48.dp, 48.dp),
            modifier = Modifier
                .weight(1f)
        ) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray)
            ) {
                BasicTextField(
                    value = textFieldValue,
                    onValueChange = onTextChanged,
                    modifier = Modifier.fillMaxSize()
                )
            }

//            UserInputTextField(
//                textFieldValue = textFieldValue,
//                onTextChanged = onTextChanged,
//                onTextFieldFocused = onTextFieldFocused,
//                keyboardType = keyboardType,
//                focusState = focusState,
//                onKeyboardDone = { onMessageSent(textFieldValue.text) },
//                modifier = Modifier.fillMaxWidth()
//            )

        }
        Spacer(modifier = Modifier.padding(horizontal = 2.dp))
        Button(
            onClick = { onMessageSent(textFieldValue.text) },
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.size(42.dp)
        ) {
            Image(
                imageVector = Icons.Default.Check,
                contentDescription = null
            )
//            Text("Send")
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
    onKeyboardDone: () -> Unit,
    modifier: Modifier = Modifier
) {
    var lastFocusState by remember { mutableStateOf(false) }

    BasicTextField(
        value = textFieldValue,
        onValueChange = { onTextChanged(it) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Send
        ),
        keyboardActions = KeyboardActions(
            onDone = { onKeyboardDone() }
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
