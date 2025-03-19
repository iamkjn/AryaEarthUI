package com.iamkjn.aryacommunication.ui.screen

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.iamkjn.aryacommunication.ui.components.MessageOverlay
import com.iamkjn.aryacommunication.ui.components.ScreenContent
import com.iamkjn.aryacommunication.ui.theme.MessagingScreenTheme
import com.iamkjn.aryacommunication.ui.theme.PrimaryGradientBottom
import com.iamkjn.aryacommunication.ui.theme.PrimaryGradientLowerMiddle
import com.iamkjn.aryacommunication.ui.theme.PrimaryGradientMiddle
import com.iamkjn.aryacommunication.ui.theme.PrimaryGradientTop
import com.iamkjn.aryacommunication.viewmodel.MessagingViewModel

/**
 * Composable function for the main messaging screen.
 *
 * This function sets up the layout and theming for the messaging screen,
 * including the background gradient and the main content area.
 *
 * @param viewModel The [MessagingViewModel] used to provide data and handle actions on this screen.
 */
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MessagingScreen(viewModel: MessagingViewModel) {
    // Apply the MessagingScreenTheme to the composable content.
    MessagingScreenTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                PrimaryGradientTop,
                                PrimaryGradientMiddle,
                                PrimaryGradientLowerMiddle,
                                PrimaryGradientBottom
                            ),
                            start = androidx.compose.ui.geometry.Offset(0f, 0f),
                            end = androidx.compose.ui.geometry.Offset(
                                1000f,
                                1000f
                            )
                        )
                    )
            ) {
                ScreenContent(viewModel)
                // Show the message overlay conditionally.
                MessageOverlay(
                    viewModel = viewModel,
                    isVisible = viewModel.isOverlayVisible,
                    onClose = viewModel::toggleOverlay
                )
            }
        }
    }
}