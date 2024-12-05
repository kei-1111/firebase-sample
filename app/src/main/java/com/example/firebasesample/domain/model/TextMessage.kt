package com.example.firebasesample.domain.model

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class TextMessage(
    val id: String = "",
    val senderId: String = "",
    val message: String = "",
    val timestamp: Long = System.currentTimeMillis(),
)

fun Long.toFormattedTime(pattern: String = "yyyy年MM月dd日HH:mm"): String {
    val formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault())
    return formatter.format(Instant.ofEpochMilli(this))
}
