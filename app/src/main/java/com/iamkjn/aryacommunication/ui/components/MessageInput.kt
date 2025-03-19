package com.iamkjn.aryacommunication.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.iamkjn.aryacommunication.R

/**
 * Composable function for the message input area at the bottom of the chat screen.
 *
 * This function creates a row with an input field and buttons for adding attachments and sending messages.
 *
 * @param messageInput The current text in the input field.
 * @param onMessageInputChange Callback for when the text in the input field changes.
 * @param onSendClick Callback for when the send button is clicked.
 * @param onPlusClick Callback for when the plus button (for attachments) is clicked.
 */
@Composable
fun MessageInput(
    messageInput: String,
    onMessageInputChange: (String) -> Unit,
    onSendClick: () -> Unit,
    onPlusClick: () -> Unit
) {
    // Main row to contain the input field and buttons.
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), // Add padding around the row.
        verticalAlignment = Alignment.CenterVertically // Center items vertically.
    ) {
        // Plus button for adding attachments.
        IconButton(onClick = onPlusClick) {
            Icon(
                painter = painterResource(id = R.drawable.icon_plus),
                contentDescription = "Menu",
                modifier = Modifier.padding(6.dp)
            ) // Add padding to the icon.
        }

        // Outlined text field for message input.
        OutlinedTextField(
            value = messageInput,
            onValueChange = onMessageInputChange,
            modifier = Modifier
                .weight(1f) // Allow the text field to take up the available space.
                .padding(horizontal = 8.dp, vertical = 4.dp) // Add padding around the text field.
                .height(50.dp), // Set a fixed height for the text field.
            placeholder = {
                // Placeholder text inside the text field.
                Text(
                    "Message",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f) // Slightly transparent text.
                )
            },
            shape = RoundedCornerShape(percent = 50), // Rounded shape for the text field.
            colors = TextFieldColors(
                focusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                cursorColor = MaterialTheme.colorScheme.onSurface,
                focusedTextColor  = MaterialTheme.colorScheme.onSurface,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                disabledContainerColor = MaterialTheme.colorScheme.surface,
                errorContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                errorTextColor = MaterialTheme.colorScheme.onSurface,
                focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0f),
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0f),
                disabledPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0f),
                errorPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0f),
                focusedLeadingIconColor = MaterialTheme.colorScheme.onSurface,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurface,
                disabledLeadingIconColor = MaterialTheme.colorScheme.onSurface,
                errorLeadingIconColor = MaterialTheme.colorScheme.onSurface,
                focusedTrailingIconColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSurface,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onSurface,
                errorTrailingIconColor = MaterialTheme.colorScheme.onSurface,
                focusedLabelColor = MaterialTheme.colorScheme.onSurface,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurface,
                disabledLabelColor = MaterialTheme.colorScheme.onSurface,
                errorLabelColor = MaterialTheme.colorScheme.onSurface,
                focusedPrefixColor = MaterialTheme.colorScheme.onSurface,
                unfocusedPrefixColor = MaterialTheme.colorScheme.onSurface,
                disabledPrefixColor = MaterialTheme.colorScheme.onSurface,
                errorPrefixColor = MaterialTheme.colorScheme.onSurface,
                focusedSuffixColor = MaterialTheme.colorScheme.onSurface,
                unfocusedSuffixColor = MaterialTheme.colorScheme.onSurface,
                disabledSuffixColor = MaterialTheme.colorScheme.onSurface,
                errorSuffixColor = MaterialTheme.colorScheme.onSurface,
                focusedSupportingTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedSupportingTextColor = MaterialTheme.colorScheme.onSurface,
                disabledSupportingTextColor = MaterialTheme.colorScheme.onSurface,
                errorSupportingTextColor = MaterialTheme.colorScheme.onSurface, errorCursorColor = MaterialTheme.colorScheme.onSurface,
                errorIndicatorColor = MaterialTheme.colorScheme.onSurface,
                textSelectionColors = TextSelectionColors(handleColor =  MaterialTheme.colorScheme.onSurface, backgroundColor = MaterialTheme.colorScheme.onSurface),
                disabledIndicatorColor = MaterialTheme.colorScheme.onSurface
            ),
            textStyle = MaterialTheme.typography.bodyMedium // Use bodyMedium text style
        )

        // Conditional send button, only visible if there is text in the input field.
        if (messageInput.isNotEmpty()) {
            IconButton(onClick = onSendClick) {
                Icon(
                    painter = painterResource(R.drawable.icon_sendmessage),
                    contentDescription = "Send"
                )
            }
        }
    }
}