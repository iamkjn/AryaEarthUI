package com.iamkjn.aryacommunication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.iamkjn.aryacommunication.R
import com.iamkjn.aryacommunication.ui.theme.PrimaryBlack
import com.iamkjn.aryacommunication.ui.theme.PrimaryWhite
import com.iamkjn.aryacommunication.ui.theme.RolloverBlue
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

//region Constants
// Define constants for padding and shape
private val MESSAGE_BUBBLE_CORNER_RADIUS = 14.dp
private val MESSAGE_BUBBLE_PADDING = 10.dp
private val MESSAGE_READ_ICON_SIZE = 14.dp
private val MESSAGE_READ_ICON_SPACING = 4.dp

// Define constants for message bubble padding
private val SENT_MESSAGE_PADDING = PaddingValues(start = 86.dp, top = 16.dp, end = 16.dp)
private val RECEIVED_MESSAGE_PADDING = PaddingValues(start = 16.dp, end = 80.dp, top = 16.dp)
//endregion

/**
 * Composable function to display a message bubble in the chat interface.
 *
 * @param message The text content of the message.
 * @param isSent Indicates if the message was sent by the current user (true) or received (false).
 * @param timestamp The timestamp of the message, used to display the message time.
 */
@Composable
fun MessageBubble(message: String, isSent: Boolean, timestamp: Long) {
    // Determine the bubble's properties based on whether it's sent or received
    val bubbleColors = getBubbleColors(isSent)
    val bubbleAlignment = getBubbleAlignment(isSent)
    val bubblePadding = getBubblePadding(isSent)
    val bubbleTextStyle = getBubbleTextStyle(isSent)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bubblePadding) // Apply appropriate padding
    ) {
        Column(
            modifier = Modifier
                .background(
                    bubbleColors.backgroundColor,
                    RoundedCornerShape(MESSAGE_BUBBLE_CORNER_RADIUS) // Rounded corners for the bubble
                )
                .padding(MESSAGE_BUBBLE_PADDING) // Padding inside the bubble
                .align(bubbleAlignment) // Align the bubble to the right or left
        ) {
            // Display the message text
            Text(
                text = message,
                style = bubbleTextStyle,
                color = bubbleColors.textColor // Text color
            )
            // Conditionally display additional information (timestamp and read icon)
            if (isSent) {
                SentMessageInfo(timestamp = timestamp)
            } else {
                ReceivedMessageInfo(timestamp = timestamp)
            }
        }
    }
}

/**
 * Data class to hold the background and text color for a message bubble.
 *
 * @property backgroundColor The background color of the message bubble.
 * @property textColor The text color of the message bubble.
 */
private data class BubbleColors(val backgroundColor: androidx.compose.ui.graphics.Color, val textColor: androidx.compose.ui.graphics.Color)

/**
 * Determines the background and text color for a message bubble.
 *
 * @param isSent Indicates if the message was sent by the user (true) or received (false).
 * @return A [BubbleColors] object containing the appropriate background and text colors.
 */
@Composable
private fun getBubbleColors(isSent: Boolean): BubbleColors {
    return if (isSent) {
        BubbleColors(
            backgroundColor = MaterialTheme.colorScheme.secondary,
            textColor = PrimaryBlack
        )
    } else {
        BubbleColors(
            backgroundColor = MaterialTheme.colorScheme.surface,
            textColor = PrimaryWhite
        )
    }
}

/**
 * Determines the horizontal alignment for a message bubble.
 *
 * @param isSent Indicates if the message was sent by the user (true) or received (false).
 * @return The appropriate horizontal alignment for the message bubble.
 */
private fun getBubbleAlignment(isSent: Boolean): Alignment.Horizontal {
    return if (isSent) Alignment.End else Alignment.Start
}

/**
 * Determines the padding values for a message bubble.
 *
 * @param isSent Indicates if the message was sent by the user (true) or received (false).
 * @return The appropriate [PaddingValues] for the message bubble.
 */
private fun getBubblePadding(isSent: Boolean): PaddingValues {
    return if (isSent) SENT_MESSAGE_PADDING else RECEIVED_MESSAGE_PADDING
}

/**
 * Determines the text style for a message bubble.
 *
 * @param isSent Indicates if the message was sent by the user (true) or received (false).
 * @return The appropriate [TextStyle] for the message bubble.
 */
@Composable
private fun getBubbleTextStyle(isSent: Boolean): TextStyle {
    return if (isSent) MaterialTheme.typography.displayMedium else MaterialTheme.typography.bodyLarge
}

/**
 * Displays the timestamp and read icon for a sent message.
 *
 * @param timestamp The timestamp of the sent message.
 */
@Composable
private fun SentMessageInfo(timestamp: Long) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.weight(1f)) // Push content to the end
        Text(
            text = formatTimestamp(timestamp), // Display the formatted timestamp
            style = MaterialTheme.typography.bodySmall,
            color = RolloverBlue // Timestamp text color
        )
        Spacer(modifier = Modifier.width(MESSAGE_READ_ICON_SPACING)) // Add space before the icon
        Icon(
            painter = painterResource(R.drawable.icon_chat_read), // Display the read icon
            contentDescription = "Read",
            modifier = Modifier.size(MESSAGE_READ_ICON_SIZE), // Set the icon size
            tint = RolloverBlue // Icon tint color
        )
    }
}

/**
 * Displays the timestamp for a received message.
 *
 * @param timestamp The timestamp of the received message.
 */
@Composable
private fun ReceivedMessageInfo(timestamp: Long) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.weight(1f)) // Push content to the end
        Text(
            text = formatTimestamp(timestamp), // Display the formatted timestamp
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f) // Timestamp text color
        )
    }
}

/**
 * Formats a timestamp into a readable time string.
 *
 * @param timestamp The timestamp to format (in milliseconds).
 * @return The formatted time string (e.g., "10:30 AM").
 */
fun formatTimestamp(timestamp: Long): String {
    val date = Date(timestamp)
    val sdf = SimpleDateFormat("h:mm a", Locale.getDefault())
    return sdf.format(date)
}