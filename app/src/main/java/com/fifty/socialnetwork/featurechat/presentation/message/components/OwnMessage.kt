package com.fifty.socialnetwork.featurechat.presentation.message.components

import android.util.Size
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fifty.socialnetwork.core.presentation.ui.theme.SpaceLarge
import com.fifty.socialnetwork.core.presentation.ui.theme.SpaceMedium

@Composable
fun OwnMessage(
    message: String,
    modifier: Modifier = Modifier,
    color: Color = Color.DarkGray,
    textColor: Color = MaterialTheme.colors.onSurface,
    formattedTime: String,
    triangleWidth: Dp = 30.dp,
    triangleHeight: Dp = 30.dp
) {
    val cornerRadius = MaterialTheme.shapes.medium.bottomEnd
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = formattedTime,
            modifier = Modifier.align(Alignment.Bottom),
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.width(SpaceLarge))
        Box(
            modifier = Modifier
                .weight(1f, false)
                .background(
                    color = color,
                    shape = MaterialTheme.shapes.medium,
                )
                .padding(SpaceMedium)
                .drawBehind {
                    val cornerRadiusPx = cornerRadius.toPx(
                        shapeSize = size,
                        density = Density(density)
                    )
                    val path = Path().apply {
                        moveTo(
                            size.width, size.height - cornerRadiusPx
                        )
                        lineTo(size.width, size.height + triangleHeight.toPx())
                        lineTo(
                            size.width - triangleWidth.toPx(), size.height - cornerRadiusPx
                        )
                        close()
                    }
                    drawPath(
                        path = path,
                        color = color
                    )
                }
        ) {
            Text(
                text = message,
                color = textColor,
            )
        }
    }
}