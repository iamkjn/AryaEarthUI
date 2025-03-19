package com.iamkjn.aryacommunication.data

import androidx.compose.ui.graphics.Color

/**
 * Data class representing a menu option in the message overlay.
 *
 * @property icon The resource ID of the icon to display for this menu option.
 * @property text The text to display for this menu option.
 * @property color The secondary color to use in the gradient background of this menu option's icon.
 * @property onClick The action to perform when this menu option is clicked.
 */
data class MenuOption(
    val icon: Int,
    val text: String,
    val color: Color,
    val onClick: () -> Unit = {} // Default action is empty
)