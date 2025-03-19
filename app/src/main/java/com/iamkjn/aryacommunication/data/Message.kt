package com.iamkjn.aryacommunication.data

/**
 * Represents a single message in the conversation.
 *
 * @property text The content of the message.
 * @property isSent `true` if the message was sent by the user, `false` if it was received.
 * @property timestamp The timestamp of when the message was sent or received (in milliseconds).
 */
data class Message(
    val text: String,
    val isSent: Boolean,
    val timestamp: Long
)