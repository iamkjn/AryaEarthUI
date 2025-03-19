package com.iamkjn.aryacommunication.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.iamkjn.aryacommunication.R
import com.iamkjn.aryacommunication.ui.theme.PrimaryWhite

// Define constants for the top bar
private val TOP_BAR_START_PADDING = 6.dp
private val TOP_BAR_TOP_PADDING = 42.dp
private val BACK_ICON_PADDING = 6.dp
private val BACK_ICON_SIZE = 20.dp
private val PROFILE_IMAGE_SIZE = 40.dp
private val SPACER_AFTER_BACK_BUTTON = 2.dp
private val SPACER_AFTER_PROFILE_IMAGE = 8.dp

/**
 * Composable function to display the top bar of a screen, typically used for navigation and branding.
 *
 * This composable provides a consistent look and feel for top bars throughout the app.
 * It includes a back button, a profile image, and a title.
 *
 * @param title The title to display in the top bar.
 * @param profileImage The drawable resource ID for the profile image.
 * @param onBackClick A lambda function to be called when the back button is clicked.
 */
@Composable
fun TopBar(title: String, profileImage: Int, onBackClick: () -> Unit = {}) {
    // Row to arrange elements horizontally
    Row(
        modifier = Modifier
            .fillMaxWidth() // Take up full available width
            .padding(start = TOP_BAR_START_PADDING, top = TOP_BAR_TOP_PADDING), // Add padding to the start and top
        verticalAlignment = Alignment.CenterVertically // Center elements vertically
    ) {
        // Back button
        BackButton(onBackClick = onBackClick)
        // Spacer between back button and profile image
        Spacer(modifier = Modifier.width(SPACER_AFTER_BACK_BUTTON))
        // Profile image
        ProfileImage(profileImage = profileImage)
        // Spacer between profile image and title
        Spacer(modifier = Modifier.width(SPACER_AFTER_PROFILE_IMAGE))
        // Title text
        TitleText(title = title)
    }
}

/**
 * Composable function to display the back button.
 *
 * @param onBackClick A lambda function to be called when the back button is clicked.
 */
@Composable
private fun BackButton(onBackClick: () -> Unit) {
    IconButton(onClick = onBackClick) {
        Icon(
            painter = painterResource(R.drawable.icon_back), // Load the back icon
            contentDescription = "Back", // Content description for accessibility
            tint = MaterialTheme.colorScheme.onBackground, // Tint color for the icon
            modifier = Modifier
                .padding(BACK_ICON_PADDING) // Add padding around the icon
                .size(BACK_ICON_SIZE) // Set the size of the icon
        )
    }
}

/**
 * Composable function to display the profile image.
 *
 * @param profileImage The drawable resource ID for the profile image.
 */
@Composable
private fun ProfileImage(profileImage: Int) {
    Image(
        painter = painterResource(id = profileImage), // Load the profile image
        contentDescription = "Profile Image", // Content description for accessibility
        modifier = Modifier
            .size(PROFILE_IMAGE_SIZE) // Set the size of the image
            .clip(CircleShape) // Clip the image to a circle shape
    )
}

/**
 * Composable function to display the title text.
 *
 * @param title The title to display.
 */
@Composable
private fun TitleText(title: String) {
    Text(
        text = title, // Set the text
        style = MaterialTheme.typography.displayMedium, // Set the text style
        color = PrimaryWhite // Set the text color
    )
}