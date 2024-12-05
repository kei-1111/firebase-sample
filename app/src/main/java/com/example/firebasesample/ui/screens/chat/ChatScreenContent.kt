package com.example.firebasesample.ui.screens.chat

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import com.example.firebasesample.domain.model.TextMessage
import com.example.firebasesample.domain.model.toFormattedTime
import com.example.firebasesample.ui.component.BodyMediumText
import com.example.firebasesample.ui.component.CenteredContainer
import com.example.firebasesample.ui.component.FirebaseSampleIcon
import com.example.firebasesample.ui.component.LabelMediumText
import com.example.firebasesample.ui.component.MessageTextField
import com.example.firebasesample.ui.screens.chat.ChatScreenDimension.MessageTextFieldMaxHeight
import com.example.firebasesample.ui.theme.dimensions.Alpha
import com.example.firebasesample.ui.theme.dimensions.Paddings

@Composable
fun ChatScreenContent(
    uiState: ChatUiState,
    messages: List<TextMessage>,
    onEvent: (ChatUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        MessageList(
            messages = messages,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(Paddings.ExtraSmall),
        )
        MessageInput(
            message = uiState.message,
            onMessageChange = { onEvent(ChatUiEvent.OnMessageInputChange(it)) },
            onSendClick = { onEvent(ChatUiEvent.OnMessageSendClick) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun MessageList(
    messages: List<TextMessage>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        reverseLayout = true,
        verticalArrangement = Arrangement.spacedBy(
            Paddings.ExtraSmall,
            Alignment.Bottom
        ),
    ) {
        items(messages.reversed()) { item ->
            MessageItem(message = item)
        }
    }
}

@Composable
private fun MessageItem(
    message: TextMessage,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = Paddings.Medium,
                vertical = Paddings.ExtraSmall
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FirebaseSampleIcon(
                icon = Icons.Rounded.Person,
                modifier = Modifier.border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface,
                    shape = CircleShape
                )
            )
            BodyMediumText(
                text = message.senderId,
                modifier = Modifier.padding(horizontal = Paddings.Small)
            )
            LabelMediumText(
                text = message.timestamp.toFormattedTime(),
                modifier = Modifier.padding(horizontal = Paddings.Small),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = Alpha.Medium)
            )
        }
        BodyMediumText(
            text = message.message,
            modifier = Modifier.padding(vertical = Paddings.Small)
        )
    }
}

@Composable
private fun MessageInput(
    message: String,
    onMessageChange: (String) -> Unit,
    onSendClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val bottomPaddingValues = WindowInsets.safeDrawing.asPaddingValues().calculateBottomPadding()

    val boxModifier = if (isKeyboardVisible()) {
        Modifier
            .padding(horizontal = Paddings.Medium, vertical = Paddings.Small)
    } else {
        Modifier
            .padding(horizontal = Paddings.Medium, vertical = Paddings.Small)
            .padding(bottom = bottomPaddingValues)
    }

    Surface(
        modifier = modifier
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Box(
            modifier = boxModifier,
        ) {
            MessageTextField(
                message = message,
                onMessageChange = onMessageChange,
                onSendClick = onSendClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeightIn(max =  MessageTextFieldMaxHeight),
            )
        }
    }
}

@Composable
private fun isKeyboardVisible(): Boolean {
    val density = LocalDensity.current

    val imeInsets = WindowInsets.ime.getBottom(density)

    val isKeyboardOpen by remember { derivedStateOf { imeInsets > 0 } }
    return isKeyboardOpen
}

