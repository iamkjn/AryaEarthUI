package com.iamkjn.aryacommunication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.iamkjn.aryacommunication.ui.screen.MessagingScreen
import com.iamkjn.aryacommunication.viewmodel.MessagingViewModel

/**
 * The main activity for the Arya Communication app.
 *
 * This activity sets up the UI and hosts the main composable screen.
 */
class MainActivity : ComponentActivity() {
    /**
     * Called when the activity is starting.
     *
     * This is where most initialization should go: calling `setContentView()`
     * to inflate the activity's UI, etc.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle
     *                           contains the data it most recently supplied in
     *                           onSaveInstanceState(Bundle).
     *                           Note: Otherwise it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable edge-to-edge display.
        enableEdgeToEdge()
        setContent {
            val viewModel: MessagingViewModel = viewModel()
            MessagingScreen(viewModel)
        }
    }
}