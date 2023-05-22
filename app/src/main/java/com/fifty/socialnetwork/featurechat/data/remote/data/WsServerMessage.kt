package com.fifty.socialnetwork.featurechat.data.remote.data

import com.fifty.socialnetwork.featurechat.domain.model.Message
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Locale

data class WsServerMessage(
    val fromId: String,
    val toId: String,
    val text: String,
    val timestamp: Long,
    val chatId: String?
) {
    fun toMessage(): Message {
        return Message(
            fromId = fromId,
            toId = toId,
            text = text,
            formattedTime = SimpleDateFormat("MMM dd, hh:mm a", Locale.getDefault())
                .format(timestamp),
            chatId = chatId,
        )
    }
}
