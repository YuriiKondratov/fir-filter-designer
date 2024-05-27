package ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InfoIconWithTooltip(
    text: String,
    alignment: Alignment = Alignment.TopCenter,
    modifier: Modifier = Modifier
) {
    TooltipArea(
        tooltipPlacement = TooltipPlacement.CursorPoint(alignment = alignment),
        tooltip = {
            Surface(
                modifier = Modifier.shadow(4.dp),
                color = Color.LightGray,
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = text,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    ) {
        Icon(
            Icons.Rounded.Info,
            contentDescription = "Info",
            modifier = modifier
        )
    }
}
