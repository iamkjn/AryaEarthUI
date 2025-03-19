package com.iamkjn.aryacommunication.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iamkjn.aryacommunication.R
import com.iamkjn.aryacommunication.data.Message
import com.iamkjn.aryacommunication.viewmodel.MessagingViewModel

/**
 * The main composable function for the screen content.
 *
 * This function sets up the Scaffold and calls [ScreenContentBody] to display the main content.
 *
 * @param viewModel The [MessagingViewModel] that provides the data and handles user interactions.
 */
@Composable
fun ScreenContent(viewModel: MessagingViewModel) {
    val messageInput by viewModel.messageInput
    val messages = viewModel.messages
    val isOverlayVisible by viewModel.isOverlayVisible.collectAsState()

    Scaffold(
        topBar = { TopBar(title = "Sarah Carter", profileImage = R.drawable.image_profile) },
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        ScreenContentBody(
            paddingValues = paddingValues,
            messages = messages,
            messageInput = messageInput,
            isOverlayVisible = isOverlayVisible,
            onMessageInputChange = viewModel::onMessageInputChange,
            onSendClick = viewModel::sendMessage,
            onPlusClick = viewModel::toggleOverlay
        )
    }
}

/**
 * The body of the screen content, containing the message list, input field, and overlay.
 *
 * This function displays the list of messages in a [LazyColumn], an input field using [MessageInput],
 * and an optional overlay ([MessageOverlay]). It manages the layout using a [Box] and a [Column].
 *
 * @param paddingValues The padding values provided by the [Scaffold].
 * @param messages The list of messages to display.
 * @param messageInput The current message input text.
 * @param isOverlayVisible Whether the overlay is currently visible.
 * @param onMessageInputChange Callback for changes in the message input field.
 * @param onSendClick Callback for when the send button is clicked.
 * @param onPlusClick Callback for when the plus button is clicked.
 * @param onCloseOverlay Callback for when the overlay is closed.
 * @param viewModel The [MessagingViewModel] for managing the overlay.
 */
@Composable
fun ScreenContentBody(
    paddingValues: PaddingValues,
    messages: List<Message>,
    messageInput: String,
    isOverlayVisible: Boolean,
    onMessageInputChange: (String) -> Unit,
    onSendClick: () -> Unit,
    onPlusClick: () -> Unit,
) {
    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(bottom = WindowInsets.ime.asPaddingValues().calculateBottomPadding())
        ) {
            val listState = rememberLazyListState()
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(paddingValues),
                reverseLayout = false,
                state = listState
            ) {
                items(items = messages, key = { it.timestamp }) { message ->
                    MessageBubble(
                        message = message.text,
                        isSent = message.isSent,
                        timestamp = message.timestamp
                    )
                }
                item {
                    Spacer(Modifier.padding(4.dp))
                }
            }

            // Automatically scroll to the bottom when a new message is added.
            LaunchedEffect(messages.size) {
                if (messages.isNotEmpty()) {
                    listState.animateScrollToItem(messages.size - 1)
                }
            }

            // Display the MessageInput when the overlay is not visible.
            AnimatedVisibility(
                visible = !isOverlayVisible,
                enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
                exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
            ) {
                MessageInput(
                    messageInput = messageInput,
                    onMessageInputChange = onMessageInputChange,
                    onSendClick = onSendClick,
                    onPlusClick = onPlusClick
                )
            }
        }
    }
}