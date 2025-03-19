package com.iamkjn.aryacommunication.ui.components

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iamkjn.aryacommunication.data.MenuOption
import com.iamkjn.aryacommunication.viewmodel.MessagingViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.roundToInt

// Constants for animation and UI
private const val ANIMATION_DURATION_MS = 500
private const val ANIMATION_DELAY_INCREMENT_MS = 50
private const val MAX_OFFSET_DP = 50f
private val SMOOTH_EASING = CubicBezierEasing(0.4f, 0.0f, 1f, 1f)
private val OVERLAY_ITEM_SIZE = 42.dp
private val OVERLAY_ICON_SIZE = 32.dp
private val OVERLAY_ICON_PADDING = 6.dp
private val OVERLAY_SPACER_WIDTH = 24.dp

/**
 * The main composable for the Message Overlay screen.
 *
 * @param viewModel The ViewModel to get menu options and other data.
 * @param isVisible A StateFlow to determine the visibility of the overlay.
 * @param onClose Callback for when the overlay should be closed.
 */
@Composable
fun MessageOverlay(
    viewModel: MessagingViewModel,
    isVisible: StateFlow<Boolean>,
    onClose: () -> Unit
) {
    val isVisibleState by isVisible.collectAsState()

    if (isVisibleState) {
        // If the overlay is visible, display the content inside a container
        OverlayContainer(onClose = onClose) {
            // Display the animated menu options
            AnimatedMenuColumn(
                menuOptions = viewModel.menuOptions,
                modifier = Modifier.padding(bottom = 8.dp)
            ) { item ->
                // Each item inside the animated column
                OverlayItem(item = item)
            }
        }
    }
}

/**
 * A container for the Message Overlay content. It provides a semi-transparent background
 * and handles the close action.
 *
 * @param onClose Callback for when the overlay should be closed.
 * @param content The content to be displayed inside the container.
 */
@Composable
private fun OverlayContainer(
    onClose: () -> Unit,
    content: @Composable () -> Unit
) {
    // The semi-transparent background
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black.copy(alpha = 0.55f)
    ) {
        // The box that allows to close the screen when clicking anywhere
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = onClose),
            contentAlignment = Alignment.BottomStart
        ) {
            // The column that contains the menu items
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .padding(
                        vertical = 24.dp,
                        horizontal = 16.dp
                    )
            ) {
                content()
            }
        }
    }
}

/**
 * Displays a list of menu options with an animated staggered reveal effect.
 *
 * @param menuOptions The list of menu options to display.
 * @param modifier Modifier for the Column.
 * @param itemContent Composable function to display each item.
 */
@Composable
fun AnimatedMenuColumn(
    menuOptions: List<MenuOption>,
    modifier: Modifier = Modifier,
    itemContent: @Composable (MenuOption) -> Unit
) {
    // State to trigger the animation
    var playAnimation by remember { mutableStateOf(false) }

    // Trigger the animation when the menu options are available
    LaunchedEffect(key1 = menuOptions) {
        playAnimation = true
    }

    Column(modifier = modifier) {
        // Display each menu option with animation
        menuOptions.forEachIndexed { index, item ->
            AnimatedItem(
                item = item,
                index = index,
                playAnimation = playAnimation,
                content = itemContent
            )
        }
    }
}

/**
 * Animates a single item in the menu column.
 *
 * @param item The menu option to animate.
 * @param index The index of the item in the list.
 * @param playAnimation True if the animation should be played.
 * @param content The composable function to display the item's content.
 */
@Composable
fun AnimatedItem(
    item: MenuOption,
    index: Int,
    playAnimation: Boolean,
    content: @Composable (MenuOption) -> Unit
) {
    // Animate the vertical offset of the item
    val animatedOffset by animateFloatAsState(
        targetValue = if (playAnimation) 0f else MAX_OFFSET_DP,
        animationSpec = tween(
            durationMillis = ANIMATION_DURATION_MS,
            delayMillis = index * ANIMATION_DELAY_INCREMENT_MS,
            easing = SMOOTH_EASING
        ), label = "Offset Animation"
    )
    // Animate the alpha of the item
    val animatedAlpha by animateFloatAsState(
        targetValue = if (playAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = ANIMATION_DURATION_MS,
            delayMillis = index * ANIMATION_DELAY_INCREMENT_MS,
            easing = SMOOTH_EASING
        ), label = "Alpha animation"
    )
    // Apply the animation to the offset and alpha of the box that contains the item
    Box(
        modifier = Modifier
            .offset { IntOffset(0, animatedOffset.roundToInt()) } // Set the offset
            .alpha(animatedAlpha) // Set the alpha
    ) {
        content(item) // Display the item's content
    }
}

/**
 * Displays a single menu item with an icon and text.
 *
 * @param item The menu option to display.
 */
@Composable
fun OverlayItem(item: MenuOption) {
    // Row for icon and text
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = item.onClick)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Display the icon
        OverlayIcon(icon = item.icon, color = item.color, contentDescription = item.text)
        // Spacer between icon and text
        Spacer(modifier = Modifier.width(OVERLAY_SPACER_WIDTH))
        // Display the text
        Text(text = item.text, fontSize = 16.sp, style = MaterialTheme.typography.bodyMedium)
    }
}

/**
 * Displays a circular icon with a gradient background.
 *
 * @param icon The icon resource ID.
 * @param color The secondary color for the gradient.
 * @param contentDescription The content description for the icon.
 */
@Composable
fun OverlayIcon(icon: Int, color: Color, contentDescription: String) {
    // Box for circular background
    Box(
        modifier = Modifier
            .size(OVERLAY_ITEM_SIZE)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF91AEBB),
                        color
                    ),
                    start = androidx.compose.ui.geometry.Offset(600f, 600f),
                    end = androidx.compose.ui.geometry.Offset(0f, 0f)
                ),
                shape = CircleShape,
            ),
        contentAlignment = Alignment.Center
    ) {
        // Icon inside the circle
        Icon(
            painter = painterResource(id = icon),
            contentDescription = contentDescription,
            modifier = Modifier
                .size(OVERLAY_ICON_SIZE)
                .padding(OVERLAY_ICON_PADDING),
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}