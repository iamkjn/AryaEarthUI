package com.iamkjn.aryacommunication.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.iamkjn.aryacommunication.R
import com.iamkjn.aryacommunication.data.MenuOption
import com.iamkjn.aryacommunication.data.Message
import com.iamkjn.aryacommunication.ui.theme.PrimaryGold
import com.iamkjn.aryacommunication.ui.theme.PrimaryGray
import com.iamkjn.aryacommunication.ui.theme.PrimaryGreen
import com.iamkjn.aryacommunication.ui.theme.PrimaryPurple
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * ViewModel for managing the messaging screen's data and interactions.
 */
class MessagingViewModel : ViewModel() {

    //region State Variables
    /**
     * State flow to track the visibility of the overlay menu.
     */
    private val _isOverlayVisible = MutableStateFlow(false)
    val isOverlayVisible: StateFlow<Boolean> = _isOverlayVisible

    /**
     * Mutable state to hold the current input text.
     */
    private val _messageInput = mutableStateOf("")
    val messageInput: androidx.compose.runtime.State<String> = _messageInput

    /**
     * Mutable state list to hold the messages in the conversation.
     */
    private val _messages = mutableStateListOf<Message>()
    val messages: List<Message> = _messages

    /**
     * Mutable state list to hold the menu options for the overlay.
     */
    private val _menuOptions = mutableStateListOf<MenuOption>()
    val menuOptions: List<MenuOption> = _menuOptions
    //endregion

    init {
        initializeConversation()
        initializeMenuOptions()
    }

    //region Initialization Functions
    /**
     * Initializes the conversation with some sample messages.
     */
    private fun initializeConversation() {
        _messages.add(
            Message(
                "Hey John, let's get together and discuss the job proposal. Does Monday Work?",
                false,
                1698743280000L // Example timestamp: 11:48 AM
            )
        )
        _messages.add(
            Message(
                "That would be great. Yes, I will see you on Monday.",
                true,
                1698750840000L // Example timestamp: 1:54 PM
            )
        )
    }

    /**
     * Initializes the menu options for the overlay.
     */
    private fun initializeMenuOptions() {
        _menuOptions.addAll(
            listOf(
                MenuOption(
                    icon = R.drawable.icon_camera,
                    text = "Camera",
                    PrimaryGray,
                    onClick = { onCameraSelected() }
                ),
                MenuOption(
                    icon = R.drawable.icon_photos,
                    text = "Photos",
                    color = PrimaryGold,
                    onClick = { onPhotosSelected() }
                ),
                MenuOption(
                    icon = R.drawable.icon_files,
                    text = "Files",
                    color = PrimaryGreen,
                    onClick = { onFilesSelected() }
                ),
                MenuOption(
                    icon = R.drawable.icon_audio,
                    text = "Audio",
                    color = PrimaryPurple,
                    onClick = { onAudioSelected() }
                )
            )
        )
    }
    //endregion

    //region Public Functions
    /**
     * Toggles the visibility of the overlay.
     */
    fun toggleOverlay() {
        _isOverlayVisible.value = !_isOverlayVisible.value
    }

    /**
     * Updates the message input state with the new text.
     *
     * @param newValue The new text from the input field.
     */
    fun onMessageInputChange(newValue: String) {
        _messageInput.value = newValue
    }

    /**
     * Sends a new message and adds it to the messages list.
     * Clears the input field after sending.
     */
    fun sendMessage() {
        if (_messageInput.value.isNotBlank()) {
            val newMessage = Message(_messageInput.value, true, System.currentTimeMillis())
            _messages.add(newMessage)
            _messageInput.value = ""
        }
    }
    //endregion

    //region Menu Option Handling
    /**
     * Handles the selection of the camera option from the overlay.
     * Closes the overlay after selection.
     */
    fun onCameraSelected() {
        // Handle camera selection
        _isOverlayVisible.value = false
    }

    /**
     * Handles the selection of the photos option from the overlay.
     * Closes the overlay after selection.
     */
    fun onPhotosSelected() {
        // Handle photos selection
        _isOverlayVisible.value = false
    }

    /**
     * Handles the selection of the files option from the overlay.
     * Closes the overlay after selection.
     */
    fun onFilesSelected() {
        // Handle files selection
        _isOverlayVisible.value = false
    }

    /**
     * Handles the selection of the audio option from the overlay.
     * Closes the overlay after selection.
     */
    fun onAudioSelected() {
        // Handle audio selection
        _isOverlayVisible.value = false
    }
    //endregion
}