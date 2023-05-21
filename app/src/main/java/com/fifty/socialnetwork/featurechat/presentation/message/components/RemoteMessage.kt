package com.fifty.socialnetwork.featurechat.presentation.message.components

import android.util.Size
import androidx.compose.foundation.background
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
import com.fifty.socialnetwork.core.presentation.ui.theme.SpaceSmall

@Composable
fun RemoteMessage(
    message: String,
    modifier: Modifier = Modifier,
    color: Color = Color.DarkGray,
    textColor: Color = Color.White,
    formattedTime: String,
    triangleWidth: Dp = 30.dp,
    triangleHeight: Dp = 30.dp
) {
    val cornerRadius = MaterialTheme.shapes.medium.bottomStart
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .weight(1f, false)
                .background(
                    color = MaterialTheme.colors.surface,
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = SpaceMedium,
                        bottomEnd = SpaceMedium,
                        bottomStart = SpaceMedium
                    ),
                )
                .padding(horizontal = SpaceMedium, vertical = SpaceSmall)
//                .drawBehind {
//                    val cornerRadiusPx = cornerRadius.toPx(
//                        shapeSize = size,
//                        density = Density(density)
//                    )
//                    val path = Path().apply {
//                        moveTo(
//                            0f, size.height - cornerRadiusPx
//                        )
//                        lineTo(0f, size.height + triangleHeight.toPx())
//                        lineTo(
//                            triangleWidth.toPx(), size.height - cornerRadiusPx
//                        )
//                        close()
//                    }
//                    drawPath(
//                        path = path,
//                        color = color
//                    )
//                }
        ) {
            Text(
                text = message,
                color = textColor
            )
        }
        Spacer(modifier = Modifier.width(SpaceLarge))
        Text(
            text = formattedTime,
            modifier = Modifier.align(Alignment.Bottom),
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colors.onSurface
        )
    }
}